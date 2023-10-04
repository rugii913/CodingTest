package programmers.lv3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// lv3 금과 은 운반하기 - 구현 중 중단, 이런 문제는 구현으로 풀면 안 된다. -> Lessons86053 코드 볼 것(이진 탐색)
public class Lessons86053X {

    private static long currentTime = 0;
    private List<City> cityList;
    private Destination destination;

    public long solution1(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        this.cityList = makeCityList(g, s, w, t);
        this.destination = new Destination(a, b);

        cityList.sort(City.sortComparatorByShipTimeAsc);

        while (!destination.isFulfilled()) {
            destination.requestGoldAndSilver(cityList);
            waitAnHour();
        }

        return currentTime;
    }

    private List<City> makeCityList(int[] g, int[] s, int[] w, int[] t) {
        List<City> tmpList = new ArrayList<>();
        for (int i = 0; i < g.length; i++) {
            tmpList.add(new City(g[i], s[i], w[i], t[i]));
        }
        return tmpList;
    }

    private void waitAnHour() {
        currentTime++;
    }

    private static class City {

        private final int goldRemain;
        private final int silverRemain;
        private Truck truckRelated;
        /*
        // 표현식을 'Integer.compare'(으)로 바꿀수 있습니다
        private static final Comparator<City> sortComparatorByShipTimeAsc = (city1, city2)
                -> city1.getTruckShipTimeOneWay() == city2.getTruckShipTimeOneWay() ? 0 :
                city1.getTruckShipTimeOneWay() > city2.getTruckShipTimeOneWay() ? 1 : -1;
        ==>
        // 'Comparator.comparingInt'(으)로 바꿀 수 있습니다
        private static final Comparator<City> sortComparatorByShipTimeAsc = (city1, city2)
                -> Integer.compare(city1.getTruckShipTimeOneWay(), city2.getTruckShipTimeOneWay());
         */
        private static final Comparator<City> sortComparatorByShipTimeAsc = Comparator.comparingInt(City::getTruckShipTimeOneWay);

        public City(int gold, int silver, int weightPerDeliver, int timePerDeliverOneWay) {
            this.goldRemain = gold;
            this.silverRemain = silver;
            this.truckRelated = new Truck(weightPerDeliver, timePerDeliverOneWay);
        }

        public int getTruckShipTimeOneWay() {
            return this.truckRelated.getTimePerDeliverOneWay();
        }

        public void readyGold(int goldNeeded) {
            if (goldRemain > goldNeeded) {
                // TODO
            }
        }

        public void readySilver(int silverNeeded) {
            // TODO
        }

        public void readyTruck(Truck truckRelated) {
            truckRelated.setTimes();
        }
    }

    private static class Truck {
        private int weightPerDeliver;
        private int timePerDeliverOneWay;
        private int weightAvailable;
        private long timeDeliverStart;
        private long timeDeliverEnd;
        private long timeReturnToCity;

        public Truck(int weightPerDeliver, int timePerDeliverOneWay) {
            this.weightPerDeliver = weightPerDeliver;
            this.timePerDeliverOneWay = timePerDeliverOneWay;
        }

        public int getTimePerDeliverOneWay() {
            return timePerDeliverOneWay;
        }

        public int getWeightAvailable() {
            return weightAvailable;
        }

        public void setTimes() {
            this.timeDeliverStart = currentTime;
            this.timeDeliverEnd = currentTime + timePerDeliverOneWay;
            this.timeReturnToCity = currentTime + timeReturnToCity;
        }
    }

    private static class Destination {
        private final int goldPurpose;
        private final int silverPurpose;
        private int goldScheduled;
        private int silverScheduled;
        private int goldNeeded;
        private int silverNeeded;
        private int goldCurrent;
        private int silverCurrent;

        public Destination(int goldPurpose, int silverPurpose) {
            this.goldPurpose = goldPurpose;
            this.silverPurpose = silverPurpose;
        }

        public boolean isFulfilled() {
            return goldCurrent == goldPurpose && silverCurrent == silverPurpose;
        }

        public void requestGoldAndSilver(List<City> cityList) {
            for (City city : cityList) {
                if (goldNeeded != 0) {
                    city.readyGold(goldNeeded);
                }

                if (silverNeeded != 0) {
                    city.readySilver(silverNeeded);
                }
            }
        }
    }
}
