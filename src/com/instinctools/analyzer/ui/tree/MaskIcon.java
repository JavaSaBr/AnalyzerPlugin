package com.instinctools.analyzer.ui.tree;

import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by ronn on 14.04.15.
 * //TODO need add documentation
 */
public class MaskIcon implements Icon {

    private final Icon delegate;

    private BufferedImage mask;

    private boolean colorPainted = true;

    public MaskIcon(final Icon delegate) {
        this(delegate, UIManager.getColor("textHighlight"), 0.5F);
    }

    public MaskIcon(final Icon delegate, final Color color) {
        this(delegate, color, 0.5F);
    }

    private MaskIcon(final Icon delegate, final Color color, final float alpha) {
        this.delegate = delegate;
        createMask(color, alpha);
    }

    private void createMask(final Color color, final float alpha) {
        mask = UIUtil.createImage(delegate.getIconWidth(), delegate.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics = (Graphics2D) mask.getGraphics();
        delegate.paintIcon(new JLabel(), graphics, 0, 0);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, alpha));
        graphics.setColor(color);
        graphics.fillRect(0, 0, mask.getWidth() - 1, mask.getHeight() - 1);
    }

    public boolean isColorPainted() {
        return colorPainted;
    }

    public void setColorPainted(final boolean colorPainted) {
        this.colorPainted = colorPainted;
    }

    public void paintIcon(final Component component, final Graphics graphics, final int x, final int y) {
        delegate.paintIcon(component, graphics, x, y);

        if (colorPainted) {
            graphics.drawImage(mask, x, y, component);
        }
    }

    public int getIconWidth() {
        return delegate.getIconWidth();
    }

    public int getIconHeight() {
        return delegate.getIconHeight();
    }
}

