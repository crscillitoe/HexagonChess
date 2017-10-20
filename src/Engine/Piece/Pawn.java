package Engine.Piece;

import Engine.Board.Board;
import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiPredicate;

public class Pawn extends Piece {

    public Pawn(HexCoordinate location, Image image, Team team) {
        super(location, PieceType.PAWN, image, team);
    }

}
