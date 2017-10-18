package Engine.Piece;

        import Engine.Board.Board;
        import Engine.Coordinate.Coordinate;
        import Engine.Coordinate.HexCoordinate;

        import java.awt.*;
        import java.util.ArrayList;
        import java.util.Collection;

public class Queen extends Piece {
    public Queen(HexCoordinate location, Image image, Team team) {
        super(location, PieceType.QUEEN, image, team);
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

        xCoord = location.getX();
        yCoord = location.getY();

        while (true) {
            try {
                toReturn.add(theBoard.makeCoordinate(++xCoord, yCoord, team));
            } catch (Exception e) {
                if (e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(xCoord, yCoord));
                }
                break;
            }
        }

        xCoord = location.getX();
        yCoord = location.getY();
        while (true) {
            try {
                toReturn.add(theBoard.makeCoordinate(--xCoord, yCoord, team));
            } catch (Exception e) {
                if (e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(xCoord, yCoord));
                }
                break;
            }
        }


        return toReturn;
    }
}
