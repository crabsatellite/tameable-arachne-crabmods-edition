package com.crabmods.tameable_arachne.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class EntityAIFollowBunnyOwner extends Goal {
    private final TamableAnimal thePet;
    private LivingEntity theOwner;
    private final Level theWorld;
    private final double speedModifier;
    private final PathNavigation petPathfinder;
    private int timeToRecalcPath;
    private final float maxDist;
    private final float minDist;
    private float oldWaterCost;

    public EntityAIFollowBunnyOwner(TamableAnimal animal, double speedModifier, float minDist, float maxDist) {
        this.thePet = animal;
        this.theWorld = animal.level();
        this.speedModifier = speedModifier;
        this.petPathfinder = animal.getNavigation();
        this.minDist = minDist;
        this.maxDist = maxDist;
        this.setFlags(java.util.EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity entitylivingbase = this.thePet.getOwner();

        if (entitylivingbase == null) {
            return false;
        } else if (entitylivingbase.isSpectator()) {
            return false;
        } else if (this.thePet.isInSittingPose()) {
            return false;
        } else if (this.thePet.distanceToSqr(entitylivingbase) < this.minDist * this.minDist) {
            return false;
        } else {
            this.theOwner = entitylivingbase;
            return true;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !this.petPathfinder.isDone() &&
               this.thePet.distanceToSqr(this.theOwner) > this.maxDist * this.maxDist &&
               !this.thePet.isInSittingPose();
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
        this.oldWaterCost = this.thePet.getPathfindingMalus(net.minecraft.world.level.pathfinder.BlockPathTypes.WATER);
        this.thePet.setPathfindingMalus(net.minecraft.world.level.pathfinder.BlockPathTypes.WATER, 0.0F);
    }

    @Override
    public void stop() {
        this.theOwner = null;
        this.petPathfinder.stop();
        this.thePet.setPathfindingMalus(net.minecraft.world.level.pathfinder.BlockPathTypes.WATER, this.oldWaterCost);
    }

    @Override
    public void tick() {
        this.thePet.getLookControl().setLookAt(this.theOwner, 10.0F, (float)this.thePet.getMaxHeadXRot());

        if (!this.thePet.isInSittingPose()) {
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);

                if (!this.petPathfinder.moveTo(this.theOwner, this.speedModifier)) {
                    if (!this.thePet.isLeashed()) {
                        if (this.thePet.distanceToSqr(this.theOwner) >= 144.0D) {
                            this.teleportToOwner();
                        }
                    }
                }
            }
        }
    }

    private void teleportToOwner() {
        int i = Mth.floor(this.theOwner.getX()) - 2;
        int j = Mth.floor(this.theOwner.getZ()) - 2;
        int k = Mth.floor(this.theOwner.getBoundingBox().minY);

        for (int l = 0; l <= 4; ++l) {
            for (int i1 = 0; i1 <= 4; ++i1) {
                if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && this.canTeleportTo(i + l, k, j + i1)) {
                    this.thePet.moveTo((double)(i + l) + 0.5D, (double)k, (double)(j + i1) + 0.5D,
                                     this.thePet.getYRot(), this.thePet.getXRot());
                    this.petPathfinder.stop();
                    return;
                }
            }
        }
    }

    private boolean canTeleportTo(int x, int y, int z) {
        BlockPos groundPos = new BlockPos(x, y - 1, z);

        // Check if there's solid ground
        if (this.theWorld.getHeight(net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, x, z) <= 0) {
            return false;
        }

        // Check all the positions that need to be clear for teleportation
        BlockPos[] positionsToCheck = {
            new BlockPos(x, y, z),         // Current position
            new BlockPos(x, y + 1, z),     // Above current
            new BlockPos(x + 1, y, z),     // East
            new BlockPos(x + 1, y + 1, z), // East above
            new BlockPos(x - 1, y, z),     // West
            new BlockPos(x - 1, y + 1, z), // West above
            new BlockPos(x, y, z + 1),     // South
            new BlockPos(x, y + 1, z + 1), // South above
            new BlockPos(x, y, z - 1),     // North
            new BlockPos(x, y + 1, z - 1), // North above
            new BlockPos(x + 1, y, z + 1), // Southeast
            new BlockPos(x + 1, y + 1, z + 1), // Southeast above
            new BlockPos(x + 1, y, z - 1), // Northeast
            new BlockPos(x + 1, y + 1, z - 1), // Northeast above
            new BlockPos(x - 1, y, z + 1), // Southwest
            new BlockPos(x - 1, y + 1, z + 1), // Southwest above
            new BlockPos(x - 1, y, z - 1), // Northwest
            new BlockPos(x - 1, y + 1, z - 1)  // Northwest above
        };

        for (BlockPos pos : positionsToCheck) {
            BlockState state = this.theWorld.getBlockState(pos);
            if (state.isSolidRender(this.theWorld, pos)) {
                return false;
            }
        }

        return true;
    }
}
