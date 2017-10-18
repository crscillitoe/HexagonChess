package Engine.Piece;

import Engine.Board.Board;
import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class King extends Piece {

    public King(HexCoordinate location, Image image, Team team) {
        super(location, PieceType.KING, image, team);
    }

    @Override
    public Collection<Coordinate> getValidMoves(Board theBoard) {
        ArrayList<Coordinate> toReturn = new ArrayList<Coordinate>();
        for(Coordinate c : location.getNeighbors()) {
           try {
              toReturn.add(theBoard.makeCoordinate(c.getX() , c.getY() , team));
           } catch(Exception e) {
               if(e.getMessage().equals("p")) {
                   toReturn.add(HexCoordinate.makeCoordinate(c.getX() , c.getY()));
               }
           }
        }
        return toReturn;
    }
}
