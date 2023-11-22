import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import io.github.downerj.Tree;
import io.github.downerj.TreeBranch;

class DrawingPanel extends JPanel {
  private void drawTrees(Graphics g, LinkedList<TreeBranch> branches) {
    final var tree = new Tree();
    while (!branches.isEmpty()) {
      final var branch = branches.remove();
      final var color = new Color(tree.getColorByDepth(branch.getDepth()));
      g.setColor(color);
      g.drawLine((int)branch.getStartX(), (int)branch.getStartY(), (int)branch.getEndX(), (int)branch.getEndY());

      if (branch.getDepth() < tree.maxDepth) {
        branches.push(branch.growBranch(TreeBranch.Direction.CCW, tree));
        branches.push(branch.growBranch(TreeBranch.Direction.CW, tree));
      }
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    final var size = getSize();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, size.width, size.height);

    final var trunkStartX = size.width / 2.;
    final var trunkStartY = size.height / 2.;
    final var numTrees = 3;
    final var initialLength = Math.min(size.width, size.height) / 6.;
    final var trunkDeltaAngle = Math.PI * 2. / numTrees;
    var branches = new LinkedList<TreeBranch>();
    for (int t = 0; t < numTrees; t++) {
      final var angle = -Math.PI / 2. + trunkDeltaAngle * t;
      final var trunkEndX = trunkStartX + Math.cos(angle) * initialLength;
      final var trunkEndY = trunkStartY + Math.sin(angle) * initialLength;
      final var depth = 0;
      branches.push(new TreeBranch(trunkStartX, trunkStartY, trunkEndX, trunkEndY, initialLength, angle, depth));
    }
    drawTrees(g, branches);
  }
}

class MainFrame extends JFrame implements KeyListener {
  MainFrame() {
    super();
    add(new DrawingPanel());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationByPlatform(true);
    pack();
    setSize(600, 600);
    addKeyListener(this);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    final var isWPressed = e.getKeyCode() == KeyEvent.VK_W;
    final var isQPressed = e.getKeyCode() == KeyEvent.VK_Q;
    final var isF4Pressed = e.getKeyCode() == KeyEvent.VK_F4;
    if (((isWPressed || isQPressed) && e.isControlDown()) || (isF4Pressed && e.isAltDown())) {
      setVisible(false);
      dispose();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void keyTyped(KeyEvent e) {}
}

public class TreeFractal {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      var frame = new MainFrame();
      frame.setVisible(true);
    });
  }
}
