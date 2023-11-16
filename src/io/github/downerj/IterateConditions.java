package io.github.downerj;

import java.awt.Color;

public class IterateConditions {
  public double branchLengthRatioCCW;
  public double branchLengthRatioCW;
  public int maxDepth;
  public int depthToSwitchColors;
  public double deltaAngleCCW;
  public double deltaAngleCW;
  public Color color1;
  public Color color2;

  public IterateConditions() {
    branchLengthRatioCCW = .6;
    branchLengthRatioCW = branchLengthRatioCCW;
    maxDepth = 8;
    depthToSwitchColors = 5;
    deltaAngleCCW = Math.PI / 3;
    deltaAngleCW = deltaAngleCCW; 
    color1 = Color.ORANGE;
    color2 = Color.GREEN;
  }

  public Color getColorByDepth(int depth) {
    return depth < depthToSwitchColors ? color1 : color2;
  }
}
