package com.instinctools.analyzer.ui.tree;

import com.instinctools.analyzer.ui.tree.model.AbstractTreeNode;
import com.instinctools.analyzer.ui.tree.model.ProjectTreeNode;
import com.intellij.ui.JBColor;
import layout.TableLayout;
import layout.TableLayoutConstants;

import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;

/**
 * Created by ronn on 14.04.15.
 * Documentation follows here.
 */
public class TreeNodeCellRenderer extends JPanel implements TreeCellRenderer {

    private static final long serialVersionUID = -4480197976519419304L;

    private static final Stroke STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{
            1,
            1
    }, 0);

    private static final int H_GAP = 4;

    private final boolean drawsFocusBorderAroundIcon;

    private final JLabel icon;

    private final ValueLabel title;
    private final ValueLabel hits;
    private final ValueLabel link;

    private Color textSelectionColor;
    private Color textNonSelectionColor;
    private Color backgroundSelectionColor;
    private Color backgroundNonSelectionColor;
    private Color borderSelectionColor;
    private Color hitsForegroundColor;

    private boolean selected;
    private boolean hasFocus;

    public TreeNodeCellRenderer() {

        final Object value = UIManager.get("Tree.drawsFocusBorderAroundIcon");

        setOpaque(false);
        setTextSelectionColor(UIManager.getColor("Tree.selectionForeground"));
        setTextNonSelectionColor(UIManager.getColor("Tree.textForeground"));
        setBackgroundSelectionColor(UIManager.getColor("Tree.selectionBackground"));
        setBackgroundNonSelectionColor(UIManager.getColor("Tree.textBackground"));
        setBorderSelectionColor(UIManager.getColor("Tree.selectionBorderColor"));
        setHitsForegroundColor(JBColor.GRAY);

        drawsFocusBorderAroundIcon = value != null && (Boolean) value;

        final double border = 0;
        final double rowsGap = 0;
        final double colsGap = H_GAP;
        final double[][] size = {
                {
                        border,
                        TableLayoutConstants.PREFERRED,
                        colsGap,
                        TableLayoutConstants.PREFERRED,
                        colsGap,
                        TableLayoutConstants.PREFERRED,
                        colsGap,
                        TableLayoutConstants.PREFERRED,
                        border
                }, // Columns
                {
                        border,
                        TableLayoutConstants.PREFERRED,
                        border
                }
        };// Rows
        final LayoutManager tbl = new TableLayout(size);
        setLayout(tbl);

        icon = new JLabel();

        title = new ValueLabel();
        title.setFont(getFont());

        hits = new ValueLabel();
        hits.setForeground(getHitsForegroundColor());
        hits.setFont(getFont());

        link = new ValueLabel();
        link.setForeground(getHitsForegroundColor());
        link.setFont(getFont());

        add(icon, "1, 1, 1, 1");
        add(title, "3, 1, 3, 1");
        add(hits, "5, 1, 5, 1");
        add(link, "7, 1, 7, 1");
    }

    final Color getHitsForegroundColor() {
        return hitsForegroundColor;
    }

    final void setHitsForegroundColor(final Color color) {
        hitsForegroundColor = color;
    }

    Color getTextSelectionColor() {
        return textSelectionColor;
    }

    final void setTextSelectionColor(final Color newColor) {
        textSelectionColor = newColor;
    }

    Color getTextNonSelectionColor() {
        return textNonSelectionColor;
    }

    final void setTextNonSelectionColor(final Color newColor) {
        textNonSelectionColor = newColor;
    }

    Color getBackgroundSelectionColor() {
        return backgroundSelectionColor;
    }

    final void setBackgroundSelectionColor(final Color newColor) {
        backgroundSelectionColor = newColor;
    }

    Color getBackgroundNonSelectionColor() {
        return backgroundNonSelectionColor;
    }

    final void setBackgroundNonSelectionColor(final Color newColor) {
        backgroundNonSelectionColor = newColor;
    }

    public Color getBorderSelectionColor() {
        return borderSelectionColor;
    }

    final void setBorderSelectionColor(final Color newColor) {
        borderSelectionColor = newColor;
    }

    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {

        if (title.getFont().getStyle() == Font.BOLD) {
            title.setFont(getFont());
        }

        if (selected && hasFocus) {
            hits.setForeground(getTextNonSelectionColor());
            hits.setBackground(getBackgroundSelectionColor());
            title.setForeground(getTextSelectionColor());
            title.setBackground(getBackgroundSelectionColor());
        } else {
            Color bColor = getBackgroundNonSelectionColor();
            if (bColor == null) {
                bColor = getBackground();
            }

            // Path highlighting
            final TreePath path = tree.getPathForRow(row);
            final TreePath selectionPath = tree.getSelectionPath();

            setBackgroundNonSelectionColor(UIManager.getColor("Tree.textBackground"));

            hits.setForeground(getHitsForegroundColor());
            hits.setBackground(bColor);
            title.setForeground(getTextNonSelectionColor());
            title.setBackground(bColor);
        }

        if (value != null) {
            setHits("");
            setLinkHtml("");

            if (value instanceof AbstractTreeNode) {

                final AbstractTreeNode node = (AbstractTreeNode) value;

                if (expanded) {
                    final MaskIcon expandedIcon = (MaskIcon) node.getExpandedIcon();
                    expandedIcon.setColorPainted(selected);
                    setIcon(expandedIcon);
                } else {
                    final MaskIcon collapsedIcon = (MaskIcon) node.getCollapsedIcon();
                    collapsedIcon.setColorPainted(selected);
                    setIcon(collapsedIcon);
                }

                setToolTipText(node.getTooltip());
                setTitle(node.getSimpleName());
            }

            if (value instanceof ProjectTreeNode) {

                final ProjectTreeNode rootNode = (ProjectTreeNode) value;

                final int bugCount = rootNode.getMarkerCount();
                final int classesCount = rootNode.getClassesCount();

                setToolTipText(rootNode.getTooltip());
                setTitle(rootNode.getSimpleName());
                setHits(bugCount == -1 ? "" : "(found " + bugCount + " bug items in " + classesCount + (classesCount == 1 ? " class)" : " classes)"));
                setLinkHtml(rootNode.getLinkHtml());
            }
        }

        this.selected = selected;
        this.hasFocus = hasFocus;

        updateBounds();

        return this;
    }

    @Override
    public void paint(final Graphics g) {

        final Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setStroke(STROKE);

        Color bColor;

        if (selected && hasFocus) {
            bColor = getBackgroundSelectionColor();
        } else {
            bColor = getBackgroundNonSelectionColor();
            if (bColor == null) {
                bColor = getBackground();
            }
        }

        int imageOffset = -1;

        if (bColor != null) {
            imageOffset = getIconLabelStart();
            g2d.setColor(bColor);
            final Dimension size = getSize();
            g2d.fillRect(imageOffset, 0, size.width - 1 - imageOffset, size.height);
        }

        if (selected && hasFocus) {
            if (drawsFocusBorderAroundIcon) {
                imageOffset = 0;
            } else if (imageOffset == -1) {
                imageOffset = getIconLabelStart();
            }
            g2d.setColor(getBorderSelectionColor());
            g2d.drawRect(imageOffset, 0, getWidth() - 1 - imageOffset, getHeight() - 1);
        } else if (selected) {
            g2d.setColor(getBorderSelectionColor());
            g2d.drawRect(imageOffset, 0, getWidth() - 1 - imageOffset, getHeight() - 1);
        }

        paintChildren(g);
    }

    private void updateBounds() {

        final Dimension size = icon.getPreferredSize();
        size.width += title.getPreferredSize().width;
        size.width += hits.getPreferredSize().width;

        if (!link.getText().isEmpty()) {
            size.width += 50; // link
        }

        size.width += H_GAP; // BorderLayout H_GAP
        size.height += 2;

        setSize(size);
        setPreferredSize(size);

        synchronized (getTreeLock()) {
            validateTree();
        }
    }

    private int getIconLabelStart() {
        return icon.getWidth() + H_GAP - 2; // icon.getX();
    }

    private void setIcon(final Icon icon) {
        this.icon.setIcon(icon);
    }

    private void setTitle(final String description) {
        title.setText(description);
    }

    private void setHits(final String stats) {
        hits.setText(stats);
    }

    private void setLinkHtml(final String html) {
        link.setText(html);
    }

    private static class ValueLabel extends JLabel {

        /**
         *
         */
        private static final long serialVersionUID = 8568080234199338166L;
        private Graphics2D _g2D;

        @Override
        public void addNotify() {
            super.addNotify();
            _g2D = (Graphics2D) getGraphics().create();
        }

        @Override
        public Dimension getPreferredSize() {
            final Dimension size = super.getPreferredSize();
            final FontMetrics fm = getFontMetrics(getFont());
            size.width = fm.stringWidth(getText()) + getInsets().left + getInsets().right + 5;
            return size;
        }
    }
}