package Engine.Piece;

        import Engine.Board.Board;
        import Engine.Coordinate.Coordinate;
        import Engine.Coordinate.HexCoordinate;

        import java.awt.*;
        import java.util.ArrayList;
        import java.util.Collection;

public class Bishop extends Piece {
    public Bishop(HexCoordinate location, Image image, Team team) {
        super(location, PieceType.BISHOP, image, team);
    }

    public Collection<Coordinate> getValidMoves(Board theBoard) {
        ArrayList<Coordinate> toReturn = new ArrayList<Coordinate>();
        int xCoord = location.getX();
        int yCoord = location.getY();

        while(true) {
            try {
                toReturn.add(theBoard.makeCoordinate(xCoord , ++yCoord , team));
            } catch(Exception e) {
                if(e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(xCoord,yCoord));
                }
                break;
            }
        }

        xCoord = location.getX();
        yCoord = location.getY();
        while(true) {
            try {
                toReturn.add(theBoard.makeCoordinate(--xCoord , ++yCoord , team));
            } catch(Exception e) {
                if(e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(xCoord,yCoord));
                }
                break;
            }
        }

        xCoord = location.getX();
        yCoord = location.getY();
        while(true) {
            try {
                toReturn.add(theBoard.makeCoordinate(xCoord , --yCoord , team));
            } catch(Exception e) {
                if(e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(xCoord,yCoord));
                }
                break;
            }
        }

        xCoord = location.getX();
        yCoord = location.getY();
        while(true) {
            try {
                toReturn.add(theBoard.makeCoordinate(++xCoord , --yCoord , team));
            } catch(Exception e) {
                if(e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(xCoord,yCoord));
                }
                break;
            }
        }

        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() + 1 , location.getY() , team));
        } catch(Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX()+1,location.getY()));
            }
        }

        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() - 1 , location.getY(), team));
        } catch(Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX()-1,location.getY()));
            }
        }

        return toReturn;
    }
}
