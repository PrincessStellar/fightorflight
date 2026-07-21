package me.rufia.fightorflight.entity.ai.tasks;

import com.cobblemon.mod.common.CobblemonMemories;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import me.rufia.fightorflight.CobblemonFightOrFlight;
import me.rufia.fightorflight.utils.FOFUtils;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.OneShot;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

public class FOFAttackAngryAtTask {
    public static OneShot<LivingEntity> create() {
        return BehaviorBuilder.create(context ->
                context.group(
                        context.present(MemoryModuleType.ANGRY_AT),
                        context.absent(MemoryModuleType.ATTACK_TARGET),
                        context.absent(CobblemonMemories.POKEMON_SLEEPING)
                ).apply(context, (angryAtAccessor, attackTargetAccessor, isSleepingAccessor) -> ((serverLevel, livingEntity, l) -> {
                    if (livingEntity instanceof PokemonEntity pokemonEntity) {
                        var angryAt = context.get(angryAtAccessor);
                        LivingEntity targetEntity = null;
                        if (serverLevel.getEntity(angryAt) instanceof LivingEntity tmp) {
                            targetEntity = tmp;
                        }
                        if (targetEntity != null) {
                            if (!FOFUtils.isPeaceful(targetEntity)) {
                                pokemonEntity.getBrain().setMemory(MemoryModuleType.ATTACK_TARGET, livingEntity);
                            } else {
                                pokemonEntity.getBrain().eraseMemory(MemoryModuleType.ANGRY_AT);
                            }
                            return true;
                        }
                    }
                    return false;
                }
                )));
    }
}
