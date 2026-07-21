package me.rufia.fightorflight.mixin;

import com.cobblemon.mod.common.api.ai.BehaviourConfigurationContext;
import com.cobblemon.mod.common.api.ai.config.task.SwitchToFightTaskConfig;
import me.rufia.fightorflight.CobblemonFightOrFlight;
import me.rufia.fightorflight.entity.ai.tasks.FOFSwitchToFightTask;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.OneShot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SwitchToFightTaskConfig.class)
public abstract class SwitchToFightTaskMixin {
    @Inject(method = "createTask(Lnet/minecraft/world/entity/LivingEntity;Lcom/cobblemon/mod/common/api/ai/BehaviourConfigurationContext;)Lnet/minecraft/world/entity/ai/behavior/OneShot;", at = @At("HEAD"), cancellable = true)
    private void createTasksMixin(LivingEntity entity, BehaviourConfigurationContext behaviourConfigurationContext, CallbackInfoReturnable<OneShot<LivingEntity>> cir) {
        if (CobblemonFightOrFlight.commonConfig().use_fof_switch_to_fight) {
            cir.setReturnValue(FOFSwitchToFightTask.createTask(entity, behaviourConfigurationContext));
        }
    }
}
