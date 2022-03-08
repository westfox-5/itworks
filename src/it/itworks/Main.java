package it.itworks;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        String filepath = args[0];

        // List<String> lines = Reader.readFile("c:/temp/data_scenarios_a_example.in");
        List<String> lines = Reader.readFile(filepath);
        lines.stream().forEach(s -> System.out.println(s));

        int currentLine = 0;
        String[] gridStr = lines.get(currentLine++).split(" ");
        Grid grid = new Grid(toInt(gridStr[0]), toInt(gridStr[1]));

        String[] assetStr = lines.get(currentLine++).split(" ");
        Asset asset = new Asset(toInt(assetStr[0]), toInt(assetStr[1]), toInt(assetStr[2]));

        List<Building> buildings = new ArrayList<>();
        for (int i = currentLine; i < asset.getBuildingNumber() + currentLine; i++ ) {
            String[] buildingStr = lines.get(currentLine).split(" ");
            Building building = new Building(toInt(buildingStr[0]), toInt(buildingStr[1]), toInt(buildingStr[2]), toInt(buildingStr[3]));
            buildings.add(building);
        }

        currentLine += asset.getBuildingNumber();

        List<Antenna> antennas = new ArrayList<>();
        for (int i = currentLine; i < asset.getAntennasNumber() + currentLine; i++ ) {
            String[] antennaStr = lines.get(currentLine).split(" ");
            Antenna antenna = new Antenna(toInt(antennaStr[0]), toInt(antennaStr[1]));
            antennas.add(antenna);
        }

    }
    
    private static int toInt(String s) {
        return Integer.parseInt(s);
    }

    private static class ComputeHelper {
        public static int distance(Element e1, Element e2) {
            return Math.abs(e1.getX() - e2.getX()) + Math.abs(e1.getY() - e2.getY());
        }

        public static int score(Building building, List<Antenna> antennaList) {
            for ( Antenna antenna : antennaList ) {
                int d = distance(building, antenna);
                if ( antenna.range <= d ) {
                    int score = 0;
                }
            }

            return -1;
        }
    }


    private static class Element {
        protected int x;
        protected int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    private static class Antenna extends Element {
        private int range;
        private int speed;

        public Antenna(int range, int speed) {
            this.range = range;
            this.speed = speed;
        }

        public int getRange() {
            return range;
        }

        public void setRange(int range) {
            this.range = range;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }
    }

    private static class Building extends Element {
        private int latency;
        private int speed;

        public Building(int x, int y, int latency, int speed) {
            this.x = x;
            this.y = y;
            this.latency = latency;
            this.speed = speed;
        }

        public int getLatency() {
            return latency;
        }

        public void setLatency(int latency) {
            this.latency = latency;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }
    }

    private static class Asset {
        private int buildNumber;
        private int antennasNumber;
        private int rewards;

        public Asset(int buildNumber, int antennasNumber, int rewards) {
            this.buildNumber = buildNumber;
            this.antennasNumber = antennasNumber;
            this.rewards = rewards;
        }

        public int getBuildingNumber() {
            return buildNumber;
        }

        public void setBuildNumber(int buildNumber) {
            this.buildNumber = buildNumber;
        }

        public int getAntennasNumber() {
            return antennasNumber;
        }

        public void setAntennasNumber(int antennasNumber) {
            this.antennasNumber = antennasNumber;
        }

        public int getRewards() {
            return rewards;
        }

        public void setRewards(int rewards) {
            this.rewards = rewards;
        }
    }

    private static class Grid {
        private int width;
        private int height;

        public Grid(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
