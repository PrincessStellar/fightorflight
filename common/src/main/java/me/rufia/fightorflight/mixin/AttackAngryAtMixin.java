package me.rufia.fightorflight.mixin;

import com.cobblemon.mod.common.entity.ai.AttackAngryAtTask;
import me.rufia.fightorflight.CobblemonFightOrFlight;
import me.rufia.fightorflight.entity.ai.tasks.FOFAttackAngryAtTask;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.OneShot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AttackAngryAtTask.class)
public abstract class AttackAngryAtMixin {
    @Inject(method = "create", at = @At("HEAD"), cancellable = true)
    private void createMixin(CallbackInfoReturnable<OneShot<? super LivingEntity>> cir) {
        if (CobblemonFightOrFlight.commonConfig().use_fof_attack_angry_at) {
            cir.setReturnValue(FOFAttackAngryAtTask.create());
        }
    }
}
