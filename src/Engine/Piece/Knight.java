package Engine.Piece;

import Engine.Board.Board;
import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiPredicate;

public class Knight extends Piece {
    public Knight(HexCoordinate location, Image image, Team team) {
        super(location, PieceType.KNIGHT, image, team);
        andLambdas = PieceLambdas.getAndLambdas(1);
        orLambdas = PieceLambdas.getOrLambdas(2);
    }
}

