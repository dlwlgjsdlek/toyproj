package project;

public class  Horse2 extends Thread implements Comparable<Horse2>{
	 private int name;
	    private int rank = 0;
	    private int location = 0;
	    public volatile boolean goal = false; // 결승지점 통과 여부
	    
		@Override
	    public void run() {
	        int cnt = 0;
	        while (true) {
	            location += cnt;
	            try {
	                Thread.sleep(1000 * (int) (Math.random() * 4));
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            if (location == 50) {
	                break;
	            }
	            cnt++;
	        }
	    }
		
		public Horse2(int name) {
			this.name = name;
		}

		public int getHName() {
			return name;
		}

		public void setHName(int name) {
			this.name = name;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public int getLocation() {
			return location;
		}

		public void setLocation(int location) {
			this.location = location;
		}

		public boolean isGoal() {
			return goal;
		}

		public void setGoal(boolean goal) {
			this.goal = goal;
		}

		@Override
		public int compareTo(Horse2 o) {
			return 0;
		}

		@Override
		public String toString() {
			return "Horse2 [name=" + name + "번마]";
		}
}
