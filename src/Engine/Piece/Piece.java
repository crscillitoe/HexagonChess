package Engine.Piece;

import Engine.Board.Board;
import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public abstract class Piece {

    HexCoordinate location;
    PieceType type;
    Image image;
    Team team;

    public Piece(HexCoordinate location, PieceType type, Image image, Team team) {
        this.location = location;
        this.type = type;
        this.image = image;
        this.team = team;
    }

    public abstract Collection<Coordinate> getValidMoves(Board theBoard);

    public Team getTeam() {
        return team;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void movePiece(Coordinate where, Board theBoard) throws Exception {
        for(Coordinate c : getValidMoves(theBoard)) {
            if(where.equals(c)) {
                this.location = (HexCoordinate)where;
                return;
            }
        }
        throw new Exception("Invalid move! You tried moving a" + type + " to a square it cannot move to!");
    }

    public PieceType getType() {
        return type;
    }

    public Image getImage() { return image; }

    private BufferedImage createResizedCopy(Image originalImage, int scaledWidth,
                                            int scaledHeight, boolean preserveAlpha )
    {
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB
                : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage( scaledWidth, scaledHeight,
                imageType );
        Graphics2D g = scaledBI.createGraphics();
        if ( preserveAlpha )
        {
            g.setComposite( AlphaComposite.Src );
        }
        g.drawImage( originalImage, 0, 0, scaledWidth, scaledHeight, null );
        g.dispose();
        return scaledBI;
    }

    public Image getScaledImage(int x , int y) {
        return createResizedCopy(image , x , y , false);
    }
}
