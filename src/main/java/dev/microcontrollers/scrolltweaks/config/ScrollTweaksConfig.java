package dev.microcontrollers.scrolltweaks.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ScrollTweaksConfig {
    public static final ConfigClassHandler<ScrollTweaksConfig> CONFIG = ConfigClassHandler.createBuilder(ScrollTweaksConfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("scrolltweaks.json"))
                    .build())
            .build();

    @SerialEntry public boolean disableScroll = false;
    @SerialEntry public boolean reverseScroll = false;
    @SerialEntry public boolean preventOverflowScroll = false;

    @SuppressWarnings("deprecation")
    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.literal("Scroll Tweaks"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Scroll Tweaks"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Disable Scrolling"))
                                .description(OptionDescription.of(Text.of("Disable scrolling altogether.")))
                                .binding(defaults.disableScroll, () -> config.disableScroll, newVal -> config.disableScroll = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Reverse Scroll Direction"))
                                .description(OptionDescription.of(Text.of("Reverse the scrolling direction, effectively switching forwards and backwards.")))
                                .binding(defaults.reverseScroll, () -> config.reverseScroll, newVal -> config.reverseScroll = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.literal("Prevent Overflow Scrolling"))
                                .description(OptionDescription.of(Text.of("Prevents you from scrolling directly between the 1st and 9th slot.")))
                                .binding(defaults.preventOverflowScroll, () -> config.preventOverflowScroll, newVal -> config.preventOverflowScroll = newVal)
                                .controller(TickBoxControllerBuilder::create)
                                .build())
                        .build()
                ))).generateScreen(parent);
    }

}
