package Engine.Piece;

import Engine.Board.Board;
import Engine.Coordinate.HexCoordinate;

public class PredicateMaterials {
    public Piece p;
    public HexCoordinate to;
    public PredicateMaterials(Piece p , HexCoordinate to) {
        this.p = p;
        this.to = to;
    }
}
