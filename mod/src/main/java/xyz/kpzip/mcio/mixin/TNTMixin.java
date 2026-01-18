package xyz.kpzip.mcio.mixin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import xyz.kpzip.mcio.communications.MIDICommunication;

@Mixin(ServerLevel.class)
public abstract class TNTMixin {
	@Shadow
	private MinecraftServer server;

	@Inject(method="explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;DDDFZLnet/minecraft/world/level/Level$ExplosionInteraction;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/core/particles/ParticleOptions;Lnet/minecraft/util/random/WeightedList;Lnet/minecraft/core/Holder;)V", at = @At(value = "TAIL"), remap = true)
	private void explodeHandler(CallbackInfo ci) {
		MIDICommunication.explodeCapacitorOn();
		CompletableFuture.delayedExecutor(100, TimeUnit.MILLISECONDS, server).execute(() -> {
			MIDICommunication.explodeCapacitorOff();
		});
	}
	

}
