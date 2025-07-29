package com.example.examplemod;

import com.mojang.blaze3d.platform.InputConstants;
import com.than00ber.simplegui.framework.Screen;
import com.than00ber.simplegui.framework.element.Element;
import com.than00ber.simplegui.framework.element.ElementHandler;
import com.than00ber.simplegui.framework.element.preset.Button;
import com.than00ber.simplegui.framework.element.preset.text.Text;
import com.than00ber.simplegui.framework.element.preset.text.cursor.Cursor;
import com.than00ber.simplegui.framework.graphics.Color;
import com.than00ber.simplegui.framework.graphics.Graphics;
import com.than00ber.simplegui.framework.layout.preset.grid.Alignment;
import com.than00ber.simplegui.framework.layout.preset.grid.Grid;
import com.than00ber.simplegui.framework.layout.preset.grid.GridConfiguration;
import com.than00ber.simplegui.framework.layout.preset.grid.Stretch;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;

public class NmScreen extends Screen {
    
    public static final KeyMapping OPEN_SCREEN = new KeyMapping("key.examplemod.open_screen", InputConstants.KEY_K, "key.examplemod.category");
    
    protected NmScreen() {
        super(Component.empty());
    }

    @Override
    public void onLayout() {
        Grid root = layout.grid();
        root.getConfiguration().grid(3, 3);

        root.add(new Element(new Rectangle(120, 120)) {
            @Override
            public void onLayout() {
                Grid screen = layout.grid();
                GridConfiguration configuration = screen.getConfiguration();
                configuration.grid(6, 3);
                configuration.getRows().gap(4);
                configuration.getCols().gap(4);
                
                Text input = new Text(new Rectangle(), "500") {
                    @Override
                    public void renderElements(Graphics graphics, Point mouse) {
                        graphics.renderBox(bounds, isHovering() || isFocused() ? Color.WHITE : Color.LIGHT_GRAY, 0);
                        graphics.renderBox(new Rectangle(bounds.x + 1, bounds.y + 1, bounds.width - 2, bounds.height -2), Color.BLACK, 0);
                        super.renderElements(graphics, mouse);
                    }
                }.foreground(Color.WHITE).cursor(Cursor::new);
                screen.add(new Button(new Rectangle(), "1", callback(1, input))).pos(1, 0).stretch(Stretch.FILL);
                screen.add(new Button(new Rectangle(), "2", callback(2, input))).pos(2, 0).stretch(Stretch.FILL);
                screen.add(new Button(new Rectangle(), "3", callback(3, input))).pos(3, 0).stretch(Stretch.FILL);
                screen.add(new Button(new Rectangle(), "4", callback(4, input))).pos(1, 1).stretch(Stretch.FILL);
                screen.add(new Button(new Rectangle(), "5", callback(5, input))).pos(2, 1).stretch(Stretch.FILL);
                screen.add(new Button(new Rectangle(), "6", callback(6, input))).pos(3, 1).stretch(Stretch.FILL);
                screen.add(new Button(new Rectangle(), "7", callback(7, input))).pos(1, 2).stretch(Stretch.FILL);
                screen.add(new Button(new Rectangle(), "8", callback(8, input))).pos(2, 2).stretch(Stretch.FILL);
                screen.add(new Button(new Rectangle(), "9", callback(9, input))).pos(3, 2).stretch(Stretch.FILL);
                screen.add(new Button(new Rectangle(), "OFF", callback(0, input))).pos(4, 0).stretch(Stretch.FILL).span(1, 3);
                screen.add(input).pos(5, 0).align(Alignment.CENTERED).stretch(Stretch.FILL).span(1, 3);
            }

            private Consumer<Button> callback(int mode, Text input) {
                return btn -> {
                    int duration = Math.toIntExact(Long.parseLong(input.getText().getString()));

                    if (Arrays.stream(NmPacket.values()).toList().get(mode).send(duration, () -> btn.status(true))) {
                        btn.status(false);

                        for (ElementHandler element : elements()) {
                            if (element instanceof Button button && button != btn) {
                                button.status(true);
                            }
                        }
                    }                        
                };
            }
        }).pos(1, 1).align(Alignment.CENTERED);
    }
}
