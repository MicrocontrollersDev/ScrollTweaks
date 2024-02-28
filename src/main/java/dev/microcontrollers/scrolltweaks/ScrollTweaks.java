package dev.microcontrollers.scrolltweaks;

import dev.microcontrollers.scrolltweaks.config.ScrollTweaksConfig;
import net.fabricmc.api.ModInitializer;

public class ScrollTweaks implements ModInitializer {
	@Override
	public void onInitialize() {
		ScrollTweaksConfig.CONFIG.load();
	}
}