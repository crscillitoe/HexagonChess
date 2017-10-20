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
        andLambdas.clear();
        orLambdas.clear();
        orLambdas.add(PieceLambdas.oneMove);
    }
}
