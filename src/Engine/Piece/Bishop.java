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
}
