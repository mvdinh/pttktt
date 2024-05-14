package Inversion;

	public class SinhVien implements Comparable<SinhVien>{
		private String ten, ho, dem;
		public SinhVien(String ho, String dem, String ten) {
			super();
			this.ten = ten;
			this.ho = ho;
			this.dem = dem;
		}
		@Override
		public int compareTo(SinhVien o) {
			int Ten = this.ten.compareTo(o.ten);
			if(Ten != 0) return Ten;
			int Dem = this.dem.compareTo(o.dem);
			if(Dem != 0) return Dem;
			int Ho = this.ho.compareTo(o.ho);
			return Ho;
		}

		@Override
		public String toString() {
			return this.ho + " " + this.dem + " " + this.ten;
		}
}
