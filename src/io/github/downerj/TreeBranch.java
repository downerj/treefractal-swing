package io.github.downerj;

public class TreeBranch {
  private final double startX;
  private final double startY;
  private final double endX;
  private final double endY;
  private final double length;
  private final double angle;
  private final int depth;

  public TreeBranch(double startX, double startY, double endX, double endY, double length, double angle, int depth) {
    this.startX = startX;
    this.startY = startY;
    this.endX = endX;
    this.endY = endY;
    this.length = length;
    this.angle = angle;
    this.depth = depth;
  }

  public enum Direction { CCW, CW }

  public TreeBranch growBranch(Direction direction, IterateConditions conditions) {
    final var newStartX = endX;
    final var newStartY = endY;
    final var newLength = length * (direction == Direction.CCW ? conditions.branchLengthRatioCCW : conditions.branchLengthRatioCW);
    final var newAngle = angle + (direction == Direction.CCW ? -conditions.deltaAngleCCW : conditions.deltaAngleCW);
    final var newEndX = newStartX + Math.cos(newAngle) * newLength;
    final var newEndY = newStartY + Math.sin(newAngle) * newLength;
    final var newDepth = depth + 1;
    return new TreeBranch(newStartX, newStartY, newEndX, newEndY, newLength, newAngle, newDepth);
  }

  public double getStartX() { return startX; }
  public double getStartY() { return startY; }
  public double getEndX() { return endX; }
  public double getEndY() { return endY; }
  public double getLength() { return length; }
  public double getAngle() { return angle; }
  public int getDepth() { return depth; }
}
