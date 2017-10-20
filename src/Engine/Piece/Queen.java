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
        andLambdas = PieceLambdas.getAndLambdas(1);
        orLambdas = PieceLambdas.getOrLambdas(4);
    }
}
