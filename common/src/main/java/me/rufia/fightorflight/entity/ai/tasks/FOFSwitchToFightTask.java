package me.rufia.fightorflight.entity.ai.tasks;

import com.cobblemon.mod.common.api.ai.BehaviourConfigurationContext;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import me.rufia.fightorflight.CobblemonFightOrFlight;
import me.rufia.fightorflight.utils.FOFUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.OneShot;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.schedule.Activity;

public class FOFSwitchToFightTask {
    public static OneShot<LivingEntity> createTask(LivingEntity entity, BehaviourConfigurationContext behaviourConfigurationContext) {
        return BehaviorBuilder.create(context ->
                context.group(
                        context.present(MemoryModuleType.ATTACK_TARGET)
                ).apply(context, (attackTargetAccessor) -> ((serverLevel, livingEntity, l) -> {
                    if (livingEntity instanceof PokemonEntity pokemonEntity) {
                        if (CobblemonFightOrFlight.commonConfig().use_fof_switch_to_fight && CobblemonFightOrFlight.commonConfig().attack_in_peaceful_difficulty) {
                            return activate(entity);
                        }
                        return original(entity);
                    }
                    return false;
                }
                )));
    }

    public static boolean original(LivingEntity entity) {
        if (FOFUtils.isPeaceful(entity)) {
            return false;
        }

        return activate(entity);
    }


    public static boolean activate(LivingEntity entity) {
        entity.getBrain().setActiveActivityIfPossible(Activity.FIGHT);
        return true;
    }
}
