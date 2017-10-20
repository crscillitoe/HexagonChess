package Engine.Piece;

import Engine.Board.Board;
import Engine.Coordinate.Coordinate;
import Engine.Coordinate.HexCoordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class PieceLambdas {
    public static BiPredicate<PredicateMaterials , Board> oneMove = (PredicateMaterials predicateMaterials , Board theBoard) -> {
        Piece p = predicateMaterials.p;
        HexCoordinate to = predicateMaterials.to;
        Team team = p.getTeam();
        Coordinate location = p.getLocation();
        if(to.distanceTo(location) == 1) {
            Piece atLocation = theBoard.getPieceAtCoord(to.getX() , to.getY());
            if(atLocation != null) {
                if((atLocation.getTeam() == Team.BLACK && team == Team.WHITE) ||
                        (atLocation.getTeam() == Team.WHITE && team == Team.BLACK)) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    };

    public static BiPredicate<PredicateMaterials , Board> twoMove = (PredicateMaterials predicateMaterials , Board theBoard) -> {
        Piece p = predicateMaterials.p;
        HexCoordinate to = predicateMaterials.to;
        Team team = p.getTeam();
        Coordinate location = p.getLocation();
        if(to.distanceTo(location) == 2) {
            Piece atLocation = theBoard.getPieceAtCoord(to.getX() , to.getY());
            if(atLocation != null) {
                if((atLocation.getTeam() == Team.BLACK && team == Team.WHITE) ||
                        (atLocation.getTeam() == Team.WHITE && team == Team.BLACK)) {
                   return true;
                }
            } else {
                return true;
            }
        }
        return false;
    };

    public static BiPredicate<PredicateMaterials , Board> threeMove = (PredicateMaterials predicateMaterials , Board theBoard) -> {
        Piece p = predicateMaterials.p;
        HexCoordinate to = predicateMaterials.to;
        Team team = p.getTeam();
        Coordinate location = p.getLocation();
        if(to.distanceTo(location) == 3) {
            Piece atLocation = theBoard.getPieceAtCoord(to.getX() , to.getY());
            if(atLocation != null) {
                if((atLocation.getTeam() == Team.BLACK && team == Team.WHITE) ||
                        (atLocation.getTeam() == Team.WHITE && team == Team.BLACK)) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    };

    public static BiPredicate<PredicateMaterials , Board> sideMove = (PredicateMaterials predicateMaterials , Board theBoard) -> {
        Piece p = predicateMaterials.p;
        HexCoordinate to = predicateMaterials.to;
        Team team = p.getTeam();
        Coordinate location = p.getLocation();
        if(to.getDirectionTo(location) == HexCoordinate.Direction.NNE || to.getDirectionTo(location) == HexCoordinate.Direction.SSW) {
            Piece atLocation = theBoard.getPieceAtCoord(to.getX() , to.getY());
            if(atLocation != null) {
                if((atLocation.getTeam() == Team.BLACK && team == Team.WHITE) ||
                        (atLocation.getTeam() == Team.WHITE && team == Team.BLACK)) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    };

    public static BiPredicate<PredicateMaterials , Board> rookMove = (PredicateMaterials predicateMaterials , Board theBoard) -> {
        Piece p = predicateMaterials.p;
        HexCoordinate to = predicateMaterials.to;
        Team team = p.getTeam();
        Coordinate location = p.getLocation();
        if(to.getDirectionTo(location) == HexCoordinate.Direction.N || to.getDirectionTo(location) == HexCoordinate.Direction.NNW
            || to.getDirectionTo(location) == HexCoordinate.Direction.S || to.getDirectionTo(location) == HexCoordinate.Direction.SSE) {
            Piece atLocation = theBoard.getPieceAtCoord(to.getX() , to.getY());
            if(atLocation != null) {
                if((atLocation.getTeam() == Team.BLACK && team == Team.WHITE) ||
                        (atLocation.getTeam() == Team.WHITE && team == Team.BLACK)) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    };


    public static Collection<BiPredicate<PredicateMaterials , Board>> getOrLambdas(int num) {
        ArrayList<BiPredicate<PredicateMaterials , Board>> toReturn = new ArrayList<BiPredicate<PredicateMaterials, Board>>();
        ArrayList<BiPredicate<PredicateMaterials , Board>> toReturnNum = new ArrayList<BiPredicate<PredicateMaterials, Board>>();
        toReturn.add(twoMove);
        toReturn.add(threeMove);
        toReturn.add(sideMove);
        toReturn.add(rookMove);

        if(num >= toReturn.size()) {
            return toReturn;
        } else {
            while(toReturnNum.size() < num) {
                int index = randInt(0 , toReturn.size() - 1);
                if(!toReturnNum.contains(toReturn.get(index))) {
                    toReturnNum.add(toReturn.get(index));
                }
            }
            return toReturnNum;
        }
    }

    public static BiPredicate<PredicateMaterials , Board> pieceBetween = (PredicateMaterials predicateMaterials , Board theBoard) -> {
        Piece p = predicateMaterials.p;
        HexCoordinate to = predicateMaterials.to;
        Team team = p.getTeam();

        if(theBoard.pieceBetween(to,p.location)) {
            return false;
        }

        return true;
    };

    public static Collection<BiPredicate<PredicateMaterials , Board>> getAndLambdas(int num) {
        ArrayList<BiPredicate<PredicateMaterials , Board>> toReturn = new ArrayList<BiPredicate<PredicateMaterials, Board>>();
        ArrayList<BiPredicate<PredicateMaterials , Board>> toReturnNum = new ArrayList<BiPredicate<PredicateMaterials, Board>>();
        toReturn.add(pieceBetween);
        if(num >= toReturn.size()) {
            return toReturn;
        } else {
            while(toReturnNum.size() < num) {
                int index = randInt(0 , toReturn.size() - 1);
                if(!toReturnNum.contains(toReturn.get(index))) {
                    toReturnNum.add(toReturn.get(index));
                }
            }
            return toReturnNum;
        }
    }

    private static int randInt(int floor, int ceiling) {
        return (int) (Math.random() * ((ceiling + 1) - floor)) + floor;
    }
}
