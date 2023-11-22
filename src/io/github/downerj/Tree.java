package io.github.downerj;

import java.util.Iterator;

public class Tree implements Iterable<TreeBranch> {
  public double branchLengthRatioCCW;
  public double branchLengthRatioCW;
  public int maxDepth;
  public int depthToSwitchColors;
  public double deltaAngleCCW;
  public double deltaAngleCW;
  public int color1;
  public int color2;

  public Tree() {
    branchLengthRatioCCW = .6;
    branchLengthRatioCW = branchLengthRatioCCW;
    maxDepth = 8;
    depthToSwitchColors = 5;
    deltaAngleCCW = Math.PI / 3.;
    deltaAngleCW = deltaAngleCCW; 
    color1 = 0xffff7700;
    color2 = 0xff00ff00;
  }

  public int getColorByDepth(int depth) {
    return depth < depthToSwitchColors ? color1 : color2;
  }

  @Override
  public Iterator<TreeBranch> iterator() {
    return null;
  }
}
