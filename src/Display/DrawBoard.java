package Display;

import Engine.Board.Board;
import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;
import Engine.Piece.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DrawBoard extends JPanel implements MouseListener {
    boolean DEBUG = false;

    Board theBoard;
    double ys = 13.3;
    double xs = 10;
    double gridWidth;
    double gridHeight;

    boolean selected = false;
    Coordinate selectedCoord;
    Piece selectedPiece;

    /**
     * Constructor for DrawBoard.
     * @param theBoard
     */
    public DrawBoard(Board theBoard) {
       this.theBoard = theBoard;
    }

    /**
     * init method for the DrawBoard class.
     * Initializes the mouseListener and paints the canvas.
     */
    public void init() {
        setFocusable(true);
        addMouseListener(this);
        requestFocusInWindow();
        repaint();
    }


    /**
     * Main paint method of the JPanel.
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        drawHexBoard(g);
        drawSelectedHex(g);
        drawWhitePieces(g);
        drawBlackPieces(g);
        drawValidMoves(g);
    }

    /**
     * Draws the background hex board to the screen.
     * @param g
     */
    private void drawHexBoard(Graphics g) {
        Image scaledImage = theBoard.getScaledHexImage(getWidth()/theBoard.getRows() , getHeight()/theBoard.getColumns());
        for(int i = 0 ; i < theBoard.getXVals().length ; i++) {
            drawImageAtLocation(scaledImage , theBoard.getXVals()[i] , theBoard.getYVals()[i] , g);
            if(DEBUG) {
                drawHexagonText(scaledImage,theBoard.getXVals()[i],theBoard.getYVals()[i],g);
            }
        }
    }

    /**
     * Draws the selected square onto the grid.
     */
    private void drawSelectedHex(Graphics g) {
        if(selected) {
            Image scaledImage = theBoard.getScaledSelectedImage(getWidth() / theBoard.getRows(), getHeight() / theBoard.getColumns());
            Coordinate gridSelected = getSquareVersion(selectedCoord.getX(), selectedCoord.getY());
            drawImageAtLocation(scaledImage, gridSelected.getX(), gridSelected.getY(), g);
        }
    }

    private void drawValidMoves(Graphics g) {
        if(selectedPiece != null) {
            Image scaledImage2 = theBoard.getScaledCanMoveImage(getWidth() / theBoard.getRows(), getHeight() / theBoard.getColumns());
            for(Coordinate c : selectedPiece.getValidMoves(theBoard)) {
                Coordinate gridSelected2 = getSquareVersion(c.getX() , c.getY());
                drawImageAtLocation(scaledImage2, gridSelected2.getX(), gridSelected2.getY(), g);
            }
        }
    }

    /**
     * Draws all of the white pieces onto the board.
     * @param g
     */
    public void drawWhitePieces(Graphics g) {
        for(Piece p : theBoard.getWhitePieces()) {
            drawPiece(p , g);
        }
    }

    /**
     * Draws all of the black pieces onto the board.
     * @param g
     */
    public void drawBlackPieces(Graphics g) {
        for(Piece p : theBoard.getBlackPieces()) {
            drawPiece(p , g);
        }
    }

    /**
     * For debugging purposes, draws the entire hex grid with coordinates labeled.
     * @param g
     */
    @Deprecated
    public void drawHexagonalGrid(Graphics g) {
        int imageWidth = getWidth()/theBoard.getRows();
        int imageHeight = getHeight()/theBoard.getColumns();
        Image scaledImage = theBoard.getScaledHexImage(getWidth()/theBoard.getRows() , getHeight()/theBoard.getColumns());
        for(int x = 0 ; x < theBoard.getRows() ; x++) {
            for(int y = 0 ; y < theBoard.getColumns() + 3 ; y++) {
                if(y % 2 == 0) {
                    if(x != theBoard.getRows() - 1) {
                        int drawX = x * (getWidth() / theBoard.getRows()) + (getWidth() / (theBoard.getRows() * 2));
                        int drawY = y * (getHeight() / theBoard.getColumns()) - ((getHeight()/(theBoard.getColumns() * 4)) * y);
                        g.drawImage(scaledImage, drawX , drawY, null);
                        String coordTextX = "X : " + x;
                        String coordTextY = "Y : " + y;
                        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                        g.drawString(coordTextX , drawX + imageWidth/2 , drawY + imageHeight/2);
                        g.drawString(coordTextY , drawX + imageWidth/2 , 25 + drawY + imageHeight/2);
                    }
                } else {
                    int drawX = x * (getWidth() / theBoard.getRows());
                    int drawY = y * (getHeight() / theBoard.getColumns()) - ((getHeight()/(theBoard.getColumns()*4)) * y);
                    g.drawImage(scaledImage, drawX , drawY, null);
                    String coordTextX = "X : " + x;
                    String coordTextY = "Y : " + y;
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                    g.drawString(coordTextX , drawX + imageWidth/2 , drawY + imageHeight/2);
                    g.drawString(coordTextY , drawX + imageWidth/2 , 25 + drawY + imageHeight/2);
                }
            }
        }
    }

    /**
     * Draws the given image at the square location on the board
     * @param scaledImage
     * @param x
     * @param y
     * @param g
     */
    private void drawImageAtLocation(Image scaledImage, int x, int y, Graphics g) {
        int imageWidth = getWidth()/theBoard.getRows();
        int imageHeight = getHeight()/theBoard.getColumns();
        if(y % 2 == 0) {
            if(x != theBoard.getRows() - 1) {
                int drawX = x * (getWidth() / theBoard.getRows()) + (getWidth() / (theBoard.getRows() * 2));
                int drawY = y * (getHeight() / theBoard.getColumns()) - ((getHeight()/(theBoard.getColumns() * 4)) * y);
                g.drawImage(scaledImage, drawX , drawY, null);
            }
        } else {
            int drawX = x * (getWidth() / theBoard.getRows());
            int drawY = y * (getHeight() / theBoard.getColumns()) - ((getHeight()/(theBoard.getColumns()*4)) * y);
            g.drawImage(scaledImage, drawX , drawY, null);
        }
    }

    /**
     * For debugging purposes only, draws the hitbox square grid over the hexagons.
     * @param g
     */
    @Deprecated
    private void drawSquareGrid(Graphics g) {
        gridWidth = getWidth()/xs;
        gridHeight = getHeight()/ys;
        g.setColor(Color.BLACK);
        for(int x = 0 ; x < xs ; x++) {
           for(int y = 0 ; y < ys ; y++) {
               if(y%2 == 0) {
                   g.drawRect((int)(x * (getWidth()/xs) + (getWidth()/(xs * 2))), (int)(y * (getHeight()/ys)), (int)gridWidth , (int)gridHeight);
               } else {
                   g.drawRect((int)(x * (getWidth()/xs)), (int)(y * (getHeight()/ys)), (int)gridWidth , (int)gridHeight);
               }
           }
        }
    }

    /**
     * Draws the given piece onto the board
     * @param p
     * @param g
     */
    private void drawPiece(Piece p , Graphics g) {
        int imageWidth = getWidth()/theBoard.getRows();
        int imageHeight = getHeight()/theBoard.getColumns();
        Image scaledImage = p.getScaledImage(getWidth()/theBoard.getRows() , getHeight()/theBoard.getColumns());
        Coordinate squarePiece = getSquareVersion(p.getLocation().getX() , p.getLocation().getY());
        drawImageAtLocation(scaledImage, squarePiece.getX(), squarePiece.getY(), g);
    }

    /**
     * FOR DEBUGGING PURPOSES ONLY
     * draws the x and y coordinates onto the given hexagon
     * @param scaledImage
     * @param x
     * @param y
     * @param g
     */
    private void drawHexagonText(Image scaledImage, int x, int y, Graphics g) {
        int imageWidth = getWidth()/theBoard.getRows();
        int imageHeight = getHeight()/theBoard.getColumns();
        if(y % 2 == 0) {
            if(x != theBoard.getRows() - 1) {
                int drawX = x * (getWidth() / theBoard.getRows()) + (getWidth() / (theBoard.getRows() * 2));
                int drawY = y * (getHeight() / theBoard.getColumns()) - ((getHeight()/(theBoard.getColumns() * 4)) * y);
                Coordinate hexVersion = getHexVersion(x,y);
                String coordTextX = "X : " + hexVersion.getX();
                String coordTextY = "Y : " + hexVersion.getY();
                g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
                g.drawString(coordTextX , drawX + imageWidth/2 , drawY + imageHeight/2);
                g.drawString(coordTextY , drawX + imageWidth/2 , 25 + drawY + imageHeight/2);
            }
        } else {
            int drawX = x * (getWidth() / theBoard.getRows());
            int drawY = y * (getHeight() / theBoard.getColumns()) - ((getHeight()/(theBoard.getColumns()*4)) * y);
            Coordinate hexVersion = getHexVersion(x,y);
            String coordTextX = "X : " + hexVersion.getX();
            String coordTextY = "Y : " + hexVersion.getY();
            g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
            g.drawString(coordTextX , drawX + imageWidth/2 , drawY + imageHeight/2);
            g.drawString(coordTextY , drawX + imageWidth/2 , 25 + drawY + imageHeight/2);
        }
    }

    /**
     * Takes in the given square coordinates, and turns it into a hexagonal coordinate system
     * @param x
     * @param y
     * @return
     */
    private Coordinate getHexVersion(int x , int y) {
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

    /**
     * Takes in the given hexagonal coordinates, and turns it into a square coordinate system
     * @param x
     * @param y
     * @return
     */
    private Coordinate getSquareVersion(int x , int y) {
        int subY = (2*y) - 6;
        int newY = y - subY;
        int subX = 0;
        switch(y) {
            case -6 :
                subX = -1;
                break;
            case -5 :
            case -4 :
                subX = -2;
                break;
            case -3 :
            case -2 :
                subX = -3;
                break;
            case -1 :
            case 0 :
                subX = -4;
                break;
            case 1 :
            case 2 :
                subX = -5;
                break;
            case 3 :
            case 4 :
                subX = -6;
                break;
            case 5 :
            case 6 :
                subX = -7;
                break;
        }

        int newX = x - subX;



        return HexCoordinate.makeCoordinate(newX , newY);
    }

    /**
     * Returns the coordinate of the hexagon that was clicked on
     * @param x
     * @param y
     * @return
     */
    private Coordinate getClickedOnHexagon(int x, int y) {
        gridWidth = getWidth()/xs;
        gridHeight = getHeight()/ys;
        int imageHeight = getHeight()/theBoard.getColumns();
        double c = imageHeight - gridHeight;
        double halfWidth = gridWidth/2;

        int row = (int) (y / gridHeight);
        int column;

        boolean rowIsOdd = row % 2 == 0;

        // Is the row an odd number?
        if (rowIsOdd)// Yes: Offset x to match the indent of the row
            column = (int) ((x - halfWidth) / gridWidth);
        else// No: Calculate normally
            column = (int) (x / gridWidth);


        // Work out the position of the point relative to the box it is in
        double relY = y - (row * gridHeight);
        double relX;

        if (rowIsOdd)
            relX = (x - (column * gridWidth)) - halfWidth;
        else
            relX = x - (column * gridWidth);


        double m = c/halfWidth;

        // Work out if the point is above either of the hexagon's top edges
        if (relY < (-m * relX) + c) // LEFT edge
        {
            row--;
            if (!rowIsOdd)
                column--;
        }
        else if (relY < (m * relX) - c) // RIGHT edge
        {
            row--;
            if (rowIsOdd)
                column++;
        }

        return getHexVersion(column,row);
    }

    /**
     * Selects the given piece if it exists.
     * @param x
     * @param y
     */
    private void selectPiece(int x , int y) {
        selectedPiece = getPieceAtCoord(x,y);
    }

    private Piece getPieceAtCoord(int x , int y) {
        for(Piece p : theBoard.getBlackPieces()) {
            if(p.getLocation().getX() == x && p.getLocation().getY() == y) {
                return p;
            }
        }

        for(Piece p : theBoard.getWhitePieces()) {
            if(p.getLocation().getX() == x && p.getLocation().getY() == y) {
                return p;
            }
        }
        return null;
    }

    private void moveSelectedPiece(int x , int y) {
        if(selectedPiece != null) {
            try {
                selectedPiece.movePiece(HexCoordinate.makeCoordinate(x,y) , theBoard);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            if(theBoard.getWhitePieces().contains(selectedPiece)) {
                if(theBoard.getWhitePieces().contains(getPieceAtCoord(x,y))) {
                    return;
                } else if(theBoard.getBlackPieces().contains(getPieceAtCoord(x,y))) {
                    theBoard.removePiece(getPieceAtCoord(x,y));
                }
            } else {
                if(theBoard.getBlackPieces().contains(getPieceAtCoord(x,y))) {
                    return;
                } else if(theBoard.getWhitePieces().contains(getPieceAtCoord(x,y))) {
                    theBoard.removePiece(getPieceAtCoord(x,y));
                }
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        Coordinate where = getClickedOnHexagon(x,y);
        if(!selected) {
            selected = true;
            selectedCoord = where;
            selectPiece(where.getX() , where.getY());
            repaint();
        } else {
            moveSelectedPiece(where.getX() , where.getY());
            selectedPiece = null;
            selected = false;
            repaint();
        }
    }


    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
