package com.intermediate.thegreatrobbery;

public class GreatRobberyApp{
    public static void main(String[] args){
        Mechanics.gameInstructions();
        Mechanics.gameSetup();
        Mechanics.gameMatch(Assets.getPlayerPool());
        }
    }
