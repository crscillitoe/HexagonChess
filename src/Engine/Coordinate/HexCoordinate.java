package Engine.Coordinate;

import java.util.ArrayList;
import java.util.Collection;

public class HexCoordinate implements Coordinate
{
    private final int x;
    private final int y;

    // Way of classifying the six directions of neighbors.
    public enum Direction {N, NNE, SSE, S, SSW, NNW};

    /**
     * Private constructor to be called by the factory method.
     */
    private HexCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creation method when given the x and y-coordinates.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the HexCoordinate instance corresponsing to (x, y)
     */
    public static HexCoordinate makeCoordinate(int x, int y)
    {
        return new HexCoordinate(x , y);
    }

    /**
     * Copy constructor creation method.
     * @param coordinate a Engine.Coordinate that may not be a HexCoordinate
     * @return the corresponding HexCoordinate or null if coord is null
     */
    public static HexCoordinate makeCoordinate(Coordinate coordinate)
    {
        return (HexCoordinate)coordinate;
    }

    /**
     * Compute the distance from this coordinate to a given coordinate.
     * There is no guarantee that the other coordinate is a HexCoordinate
     * instance.
     * @param other the other coordinate
     * @return the distance from this to the other coordinate
     */
    public int distanceTo(Coordinate other)
    {
        int otherZ = -1 * (other.getX() + other.getY());
        int thisZ = -1 * (getX() + getY());

        int deltaY = Math.abs(other.getY() - getY());
        int deltaX = Math.abs(other.getX() - getX());
        int deltaZ = Math.abs(otherZ - thisZ);

        return Math.max(deltaY , Math.max(deltaX , deltaZ));
    }

    /**
     * Determine if this coordinate is adjacent to the specified coordinate.
     * There is no guarantee that the other coordinate is a HexCoordinate
     * instance.
     * @param other the coordinate to check for adjacenty to this coordinate
     * @return true if other is adjacent to this coordinate, false otherwise
     */
    public boolean isAdjacentTo(Coordinate other)
    {
        return distanceTo(other) == 1;
    }

    /**
     * Determine the direction of the parameter coordinate to this coordinate if it
     * is in a straight line. If it not in a straight line, this method returns null.
     * There is no guarantee that the other coordinate is a HexCoordinate
     * instance.
     * @param other
     * @return the direction of other relative to this or null if not in a straight line
     */
    public Direction getDirectionTo(Coordinate other)
    {
        if(other.equals(this)) return null;

        int deltaY = Math.abs(other.getY() - getY());
        int deltaX = Math.abs(other.getX() - getX());
        if(deltaY == 0) {
            if(other.getX() > getX()) {
                return Direction.NNE;
            } else {
                return Direction.SSW;
            }
        } else if(deltaX == 0) {
            if(other.getY() > getY()) {
                return Direction.N;
            } else {
                return Direction.S;
            }
        } else if(deltaX == deltaY) {
            if(other.getX() > getX()) {
                return Direction.SSE;
            } else {
                return Direction.NNW;
            }
        }


        return null;
    }

    /**
     * @return an array containing all of the coordinates (6 of them) that are
     * 	adjacent to this coordinate.
     */
    public Collection<Coordinate> getNeighbors()
    {
        ArrayList<Coordinate> neighbors = new ArrayList<Coordinate>();
        neighbors.add(makeCoordinate(getX() + 1 , getY()));
        neighbors.add(makeCoordinate(getX() - 1 , getY()));
        neighbors.add(makeCoordinate(getX() , getY() + 1));
        neighbors.add(makeCoordinate(getX() , getY() - 1));
        neighbors.add(makeCoordinate(getX() + 1 , getY() - 1));
        neighbors.add(makeCoordinate(getX() - 1 , getY() + 1));

        return neighbors;
    }

    /*
     * @see hexcoordinate.Engine.Coordinate#getX()
     */
    @Override
    public int getX()
    {
        return x;
    }

    /*
     * @see hexcoordinate.Engine.Coordinate#getY()
     */
    @Override
    public int getY()
    {
        return y;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        HexCoordinate other = (HexCoordinate) obj;
        if (x != other.x) {
            return false;
        }
        if (y != other.y) {
            return false;
        }
        return true;
    }

}
