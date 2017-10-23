package Engine.Board;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;
import Engine.Piece.*;

import javax.swing.*;

public class Board {

    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;
    private Image hexagonImage;
    private Image selectedImage;
    private Image canMoveImage;
    private final int rows = 10;
    private final int columns = 10;
    private int turn;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public static int[] getXVals() {
        int[] toReturn = {3,4,5,3,4,5,6,2,3,4,5,6,2,3,4,5,6,7,1,2,3,4,5,6,7,1,2,3,4,5,6,7,8,0,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,2,3,4,5,6,7,2,3,4,5,6,3,4,5,6,3,4,5};
        return toReturn;
    }

    public static int[] getYVals() {
       int[] toReturn = {12,12,12,11,11,11,11,10,10,10,10,10,9,9,9,9,9,9,8,8,8,8,8,8,8,7,7,7,7,7,7,7,7,6,6,6,6,6,6,6,6,6,5,5,5,5,5,5,5,5,4,4,4,4,4,4,4,3,3,3,3,3,3,2,2,2,2,2,1,1,1,1,0,0,0};
       return toReturn;
    }

    public static Collection<Coordinate> getValidCoords() {
        ArrayList<Coordinate> validBoardSquares = new ArrayList<Coordinate>();

        for(int i = 0 ; i < getXVals().length ; i++) {
            validBoardSquares.add(getHexVersion(getXVals()[i] , getYVals()[i]));
        }
        return validBoardSquares;
    }

    public int getTurn() { return turn; }

    public void nextTurn() {
        turn++;
    }

    public Board() {
        turn = 1;
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();

        hexagonImage = new ImageIcon(Board.class.getResource("../../Display/Shapes/Hexagon.png")).getImage();
        selectedImage = new ImageIcon(Board.class.getResource("../../Display/Shapes/SelectedHexagon.png")).getImage();
        canMoveImage = new ImageIcon(Board.class.getResource("../../Display/Shapes/CanMove.png")).getImage();
        Image BlackKing = new ImageIcon(Board.class.getResource("../../Display/Shapes/BlackKing.png")).getImage();
        Image BlackQueen = new ImageIcon(Board.class.getResource("../../Display/Shapes/BlackQueen.png")).getImage();
        Image BlackKnight = new ImageIcon(Board.class.getResource("../../Display/Shapes/BlackKnight.png")).getImage();
        Image BlackBishop = new ImageIcon(Board.class.getResource("../../Display/Shapes/BlackBishop.png")).getImage();
        Image BlackRook = new ImageIcon(Board.class.getResource("../../Display/Shapes/BlackRook.png")).getImage();
        Image BlackPawn = new ImageIcon(Board.class.getResource("../../Display/Shapes/BlackPawn.png")).getImage();

        Image WhiteKing = new ImageIcon(Board.class.getResource("../../Display/Shapes/WhiteKing.png")).getImage();
        Image WhiteQueen = new ImageIcon(Board.class.getResource("../../Display/Shapes/WhiteQueen.png")).getImage();
        Image WhiteKnight = new ImageIcon(Board.class.getResource("../../Display/Shapes/WhiteKnight.png")).getImage();
        Image WhiteBishop = new ImageIcon(Board.class.getResource("../../Display/Shapes/WhiteBishop.png")).getImage();
        Image WhiteRook = new ImageIcon(Board.class.getResource("../../Display/Shapes/WhiteRook.png")).getImage();
        Image WhitePawn = new ImageIcon(Board.class.getResource("../../Display/Shapes/WhitePawn.png")).getImage();

        Knight blackKnight = new Knight(HexCoordinate.makeCoordinate(3 , -5) , BlackKnight , Team.BLACK);
        Knight blackKnight2 = new Knight(HexCoordinate.makeCoordinate(2 , -5) , BlackKnight , Team.BLACK);
        King blackKing = new King(HexCoordinate.makeCoordinate(2,-6) , BlackKing, Team.BLACK);
        Rook blackRook = new Rook(HexCoordinate.makeCoordinate(1,-5) , BlackRook, Team.BLACK);
        Rook blackRook2 = new Rook(HexCoordinate.makeCoordinate(4,-6) , BlackRook, Team.BLACK);
        Bishop blackBishop  = new Bishop(HexCoordinate.makeCoordinate(4,-5) , BlackBishop , Team.BLACK);
        Queen blackQueen = new Queen(HexCoordinate.makeCoordinate(3,-6) , BlackQueen , Team.BLACK);
        Pawn blackPawn1 = new Pawn(HexCoordinate.makeCoordinate(0,-4) , BlackPawn , Team.BLACK);
        Pawn blackPawn2 = new Pawn(HexCoordinate.makeCoordinate(1,-4) , BlackPawn , Team.BLACK);
        Pawn blackPawn3 = new Pawn(HexCoordinate.makeCoordinate(2,-4) , BlackPawn , Team.BLACK);
        Pawn blackPawn4 = new Pawn(HexCoordinate.makeCoordinate(3,-4) , BlackPawn , Team.BLACK);
        Pawn blackPawn5 = new Pawn(HexCoordinate.makeCoordinate(4,-4) , BlackPawn , Team.BLACK);
        blackPieces.add(blackKing);
        blackPieces.add(blackKnight2);
        blackPieces.add(blackRook);
        blackPieces.add(blackKnight);
        blackPieces.add(blackBishop);
        blackPieces.add(blackRook2);
        blackPieces.add(blackQueen);
        blackPieces.add(blackPawn1);
        blackPieces.add(blackPawn2);
        blackPieces.add(blackPawn3);
        blackPieces.add(blackPawn4);
        blackPieces.add(blackPawn5);

        Knight whiteKnight = new Knight(HexCoordinate.makeCoordinate(-3 , 5) , WhiteKnight , Team.WHITE);
        Knight whiteKnight2 = new Knight(HexCoordinate.makeCoordinate(-2 , 5) , WhiteKnight , Team.WHITE);
        King whiteKing = new King(HexCoordinate.makeCoordinate(-2,6) , WhiteKing, Team.WHITE);
        Rook whiteRook = new Rook(HexCoordinate.makeCoordinate(-1,5) , WhiteRook, Team.WHITE);
        Rook whiteRook2 = new Rook(HexCoordinate.makeCoordinate(-4,6) , WhiteRook, Team.WHITE);
        Bishop whiteBishop  = new Bishop(HexCoordinate.makeCoordinate(-4,5) , WhiteBishop , Team.WHITE);
        Queen whiteQueen = new Queen(HexCoordinate.makeCoordinate(-3,6) , WhiteQueen , Team.WHITE);
        Pawn whitePawn1 = new Pawn(HexCoordinate.makeCoordinate(0,4) , WhitePawn , Team.WHITE);
        Pawn whitePawn2 = new Pawn(HexCoordinate.makeCoordinate(-1,4) , WhitePawn , Team.WHITE);
        Pawn whitePawn3 = new Pawn(HexCoordinate.makeCoordinate(-2,4) , WhitePawn , Team.WHITE);
        Pawn whitePawn4 = new Pawn(HexCoordinate.makeCoordinate(-3,4) , WhitePawn , Team.WHITE);
        Pawn whitePawn5 = new Pawn(HexCoordinate.makeCoordinate(-4,4) , WhitePawn , Team.WHITE);
        whitePieces.add(whiteKing);
        whitePieces.add(whiteKnight2);
        whitePieces.add(whiteRook);
        whitePieces.add(whiteKnight);
        whitePieces.add(whiteBishop);
        whitePieces.add(whiteRook2);
        whitePieces.add(whiteQueen);
        whitePieces.add(whitePawn1);
        whitePieces.add(whitePawn2);
        whitePieces.add(whitePawn3);
        whitePieces.add(whitePawn4);
        whitePieces.add(whitePawn5);
    }

    public void removePiece(Piece p) {
        if(blackPieces.contains(p)) {
            blackPieces.remove(p);
        } else {
            whitePieces.remove(p);
        }
    }

    public Piece getPieceAtCoord(int x , int y) {
        for(Piece p : whitePieces) {
            if(p.getLocation().getX() == x && p.getLocation().getY() == y) {
                return p;
            }
        }
        for(Piece p : blackPieces) {
            if(p.getLocation().getX() == x && p.getLocation().getY() == y) {
                return p;
            }
        }
        return null;
    }

    public Coordinate makeCoordinate(int x , int y , Team team) throws Exception {
        boolean doReturn = false;
        for(Coordinate c : getValidCoords()) {
             if(c.getX() == x && c.getY() == y) {
                 if(team.equals(Team.WHITE)) {
                     for (Piece p : whitePieces) {
                         if (p.getLocation().getX() == x && p.getLocation().getY() == y) {
                             throw new Exception("out of bounds!");
                         }
                     }

                     for (Piece p : blackPieces) {
                         if (p.getLocation().getX() == x && p.getLocation().getY() == y) {
                             throw new Exception("p");
                         }
                     }
                 } else {
                     for (Piece p : whitePieces) {
                         if (p.getLocation().getX() == x && p.getLocation().getY() == y) {
                             throw new Exception("p");
                         }
                     }

                     for (Piece p : blackPieces) {
                         if (p.getLocation().getX() == x && p.getLocation().getY() == y) {
                             throw new Exception("out of bounds!");
                         }
                     }

                 }
                doReturn = true;
             }
        }
        if(doReturn) {
            return HexCoordinate.makeCoordinate(x,y);
        } else {
            throw new Exception("out of bounds!");
        }
    }

    public Image getHexagonImage() {
        return hexagonImage;
    }

    public Collection<Piece> getWhitePieces() {
        return whitePieces;
    }

    public Collection<Piece> getBlackPieces() {
        return blackPieces;
    }

    public boolean pieceBetween(HexCoordinate c1 , Coordinate c2) {
        if(c1.distanceTo(c2) == 1) {
            return false;
        }
        Collection<Coordinate> inBetween = inBetween(c1,c2);
        if(inBetween == null) {
            return false;
        }
        for(Coordinate c : inBetween) {
            if(getPieceAtCoord(c.getX() , c.getY()) != null) {
               return true;
            }
        }

        return false;
    }

    private Collection<Coordinate> inBetween(HexCoordinate c1 , Coordinate c2) {
        HexCoordinate.Direction dir = c1.getDirectionTo(c2);
        Collection<Coordinate> toReturn = new ArrayList<Coordinate>();
        if (dir == HexCoordinate.Direction.S) {
            int x = c1.getX();
            int y = c1.getY() - 1;
            while(y != c2.getY()) {
                toReturn.add(HexCoordinate.makeCoordinate(x , y));
                y--;
            }
        } else if (dir == HexCoordinate.Direction.N) {
            int x = c1.getX();
            int y = c1.getY() + 1;
            while(y != c2.getY()) {
                toReturn.add(HexCoordinate.makeCoordinate(x , y));
                y++;
            }
        } else if (dir == HexCoordinate.Direction.NNE) {
            int x = c1.getX() + 1;
            int y = c1.getY();
            while(x != c2.getX()) {
                toReturn.add(HexCoordinate.makeCoordinate(x , y));
                x++;
            }
        } else if (dir == HexCoordinate.Direction.NNW) {
            int x = c1.getX() - 1;
            int y = c1.getY() + 1;
            while(x != c2.getX()) {
                toReturn.add(HexCoordinate.makeCoordinate(x , y));
                x--;
                y++;
            }
        } else if (dir == HexCoordinate.Direction.SSE) {
            int x = c1.getX() + 1;
            int y = c1.getY() - 1;
            while(x != c2.getX()) {
                toReturn.add(HexCoordinate.makeCoordinate(x , y));
                x++;
                y--;
            }
        } else if (dir == HexCoordinate.Direction.SSW) {
            int x = c1.getX() - 1;
            int y = c1.getY();
            while(x != c2.getX()) {
                toReturn.add(HexCoordinate.makeCoordinate(x , y));
                x--;
            }
        }
        if(toReturn.size() == 0) {
            return null;
        }
        return toReturn;
    }

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

    public Image getScaledHexImage(int x , int y) {
        return createResizedCopy(hexagonImage , x , y , false);
    }

    public Image getScaledSelectedImage(int x , int y) {
        return createResizedCopy(selectedImage , x , y , false);
    }

    public Image getScaledCanMoveImage(int x , int y) {
        return createResizedCopy(canMoveImage, x, y, false);
    }

    private static Coordinate getHexVersion(int x , int y) {
        int yDelta = y - 6;
        int subY = (yDelta*2) + 6;
        int newY = y - subY;
        int subX = 0;
        switch(y) {
            case 12 :
                subX = 1;
                break;
            case 11 :
            case 10 :
                subX = 2;
                break;
            case 9 :
            case 8 :
                subX = 3;
                break;
            case 7 :
            case 6 :
                subX = 4;
                break;
            case 5 :
            case 4 :
                subX = 5;
                break;
            case 3 :
            case 2 :
                subX = 6;
                break;
            case 1 :
            case 0 :
                subX = 7;
                break;
        }

        int newX = x - subX;

        return HexCoordinate.makeCoordinate(newX , newY);
    }
}
