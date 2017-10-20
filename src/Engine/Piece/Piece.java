package Engine.Piece;

import Engine.Board.Board;
import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiPredicate;

public abstract class Piece {

    HexCoordinate location;
    PieceType type;
    Image image;
    Team team;
    Collection<BiPredicate<PredicateMaterials , Board>> andLambdas = new ArrayList<BiPredicate<PredicateMaterials, Board>>();
    Collection<BiPredicate<PredicateMaterials , Board>> orLambdas = new ArrayList<BiPredicate<PredicateMaterials, Board>>();

    public Piece(HexCoordinate location, PieceType type, Image image, Team team) {
        this.location = location;
        this.type = type;
        this.image = image;
        this.team = team;
        andLambdas = PieceLambdas.getAndLambdas(1);
        orLambdas = PieceLambdas.getOrLambdas(1);
        orLambdas.add(PieceLambdas.oneMove);
    }

    public Collection<Coordinate> getValidMoves(Board theBoard) {

        ArrayList<Coordinate> toReturn = new ArrayList<Coordinate>();

        for(Coordinate c : Board.getValidCoords()) {
            PredicateMaterials materials = new PredicateMaterials(this , (HexCoordinate)c);
            boolean add = true;
            for(BiPredicate<PredicateMaterials , Board> predicate : andLambdas) {
                if(!predicate.test(materials , theBoard)) {
                    add = false;
                }
            }

            if(add) {
                if(!orLambdas.isEmpty()) {
                    for (BiPredicate<PredicateMaterials, Board> predicate : orLambdas) {
                        if (predicate.test(materials, theBoard)) {
                            toReturn.add(c);
                        }
                    }
                } else {
                    toReturn.add(c);
                }
            }
        }

        return toReturn;
    }

    public Team getTeam() {
        return team;
    }

    public Coordinate getLocation() {
        return location;
    }

    public void movePiece(Coordinate where, Board theBoard) throws Exception {
        for(Coordinate c : getValidMoves(theBoard)) {
            if(where.equals(c)) {
                Piece atCoord = theBoard.getPieceAtCoord(where.getX() , where.getY());
                if(atCoord != null) {
                   theBoard.removePiece(atCoord);
                }
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
