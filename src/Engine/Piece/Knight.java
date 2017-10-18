package Engine.Piece;

import Engine.Board.Board;
import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Knight extends Piece {

    public Knight(HexCoordinate location, Image image, Team team) {
        super(location, PieceType.KNIGHT, image, team);
    }

    @Override
    public Collection<Coordinate> getValidMoves(Board theBoard) {
        Collection<Coordinate> toReturn = new ArrayList<Coordinate>();
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() + 2 , location.getY() , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() + 2, location.getY()));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() + 2 , location.getY() - 1 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() + 2, location.getY() - 1));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() + 2 , location.getY() - 2 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() + 2, location.getY() - 2));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() + 1 , location.getY() - 2 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() + 1, location.getY() - 2));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() + 1 , location.getY() + 1 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() + 1, location.getY() + 1));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() , location.getY() + 2 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() , location.getY() + 2));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() , location.getY() - 2 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() , location.getY() -2));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() - 1 , location.getY() + 2 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() - 1, location.getY()+2));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() - 1 , location.getY() - 1 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() -1, location.getY()-1));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() - 2 , location.getY() + 2 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() -2, location.getY()+ 2));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() - 2 , location.getY() + 1 , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() -2 , location.getY() + 1));
            }
        }
        try {
            toReturn.add(theBoard.makeCoordinate(location.getX() - 2 , location.getY() , team));
        } catch (Exception e) {
            if(e.getMessage().equals("p")) {
                toReturn.add(HexCoordinate.makeCoordinate(location.getX() - 2, location.getY()));
            }
        }

        return toReturn;
    }
}

