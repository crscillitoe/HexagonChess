package Engine.Piece;

import Engine.Board.Board;
import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Pawn extends Piece {

    public Pawn(HexCoordinate location, Image image, Team team) {
        super(location, PieceType.PAWN, image, team);
    }


    @Override
    public Collection<Coordinate> getValidMoves(Board theBoard) {
        ArrayList<Coordinate> toReturn = new ArrayList<Coordinate>();
        if(team.equals(Team.WHITE)) {
            try {
                toReturn.add(theBoard.makeCoordinate(location.getX() + 1 , location.getY()-1 , team));
            } catch(Exception e) {
                if(e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(location.getX()+1,location.getY()-1));
                }
            }

            try {
                toReturn.add(theBoard.makeCoordinate(location.getX() , location.getY()-1, team));
            } catch(Exception e) {
                if(e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(location.getX(),location.getY()-1));
                }
            }

        } else {
            try {
                toReturn.add(theBoard.makeCoordinate(location.getX() - 1 , location.getY()+1 , team));
            } catch(Exception e) {
                if(e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(location.getX()-1,location.getY()+1));
                }
            }

            try {
                toReturn.add(theBoard.makeCoordinate(location.getX() , location.getY()+1, team));
            } catch(Exception e) {
                if(e.getMessage().equals("p")) {
                    toReturn.add(HexCoordinate.makeCoordinate(location.getX(),location.getY()+1));
                }
            }

        }

        return toReturn;
    }
}
