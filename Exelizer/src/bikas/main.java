package bikas;



	
	
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
	import java.util.Scanner;

	public class main {

		public static void main(String[] args) throws FileNotFoundException {
			new main().run();
		}

		private void run() throws FileNotFoundException {
			ArrayList<Double> time = new ArrayList<Double>();
			ArrayList<Double> buffer = new ArrayList<Double>();
			ArrayList<Integer> repId = new ArrayList<Integer>();
			ArrayList<Integer> repBr = new ArrayList<Integer>();

			ArrayList<String> testnames = new ArrayList<String>();
//			testnames.add("lia");
//			testnames.add("olia");
//			testnames.add("balia");
//			testnames.add("wvegas");
			testnames.add("cubic");
//			testnames.add("reno");
//			testnames.add("SVC");
//			testnames.add("MDC");
			
			for (int c = 1; c <= 1; c++) {
				for (int t = 0; t < testnames.size(); t++) {

					String testname = "t" + c + "_" + "client1_" + testnames.get(t) + ".txt";
					String filename = "/home/korhan/Desktop/schedular/Tests/0ms_difference/" + testname;
					Scanner sc = new Scanner(new File(filename));
					while (sc.hasNext()) {
						String line = sc.nextLine();
						if (line.contains("DDD")) {
							String left = line.split("\", source")[0];
							String metrics = left.split("\t")[1];
							// System.out.println(line);
							// System.out.println(left);
							// System.out.println(metrics);

							String x = (left.split("]DDD")[0]);
							String y = x.split("\\[")[2];
							// System.out.println(time);

							String[] metricarr = metrics.split(",");

							time.add(Double.parseDouble(y));
							buffer.add(Double.parseDouble(metricarr[0]));
							repId.add(Integer.parseInt(metricarr[1]));
							repBr.add(Integer.parseInt(metricarr[2]));
						}
					}

					ArrayList<Integer> reprcount = new ArrayList<Integer>();
					for (int i = 0; i < 10; i++) {
						reprcount.add(0);
					}

					int underrunCount = 0;
					int underrunDuration = 0;
					PrintWriter pw = new PrintWriter(new File("/home/korhan/Desktop/schedular/Tests/0ms_difference/Result_" + testname));
					int repChange = 0;
					int currRep = repId.get(0);
					boolean underrunFlag = false;
					int totalBr = 0;
					int avgBr = 0;

					for (int i = 0; i < time.size(); i++) {
						System.out.println(time.get(i) + "\t" + buffer.get(i) + "\t" + repId.get(i) + "\t" + repBr.get(i));
						if (currRep != repId.get(i)) {
							repChange += Math.abs((currRep - repId.get(i)));
							currRep = repId.get(i);
							System.out.println(i + " "+repChange);
						}

						if (i < 596) {
							int x = reprcount.get(repId.get(i));
							reprcount.set(repId.get(i), x + 1);

							if (buffer.get(i) < 1 && buffer.get(i) > 0) {
								System.out.println(i);
								if (underrunFlag == false) {
									underrunFlag = true;
									underrunCount++;
								} else {
									underrunDuration++;
								}
							} else if (underrunFlag) {
								underrunFlag = false;
								underrunDuration++;
							}

							totalBr += repBr.get(i);

						}
					}
					avgBr = totalBr / 596;
					System.out.println("Representation Change Count : " + repChange);
					System.out.println(underrunCount + "," + underrunDuration);
					pw.println("Representation Change Count : " + repChange);
					pw.println("Representation Count : " + reprcount);
					pw.println("Total Underrun Count : " + underrunCount);
					pw.println("Total Underrun Duration : " + underrunDuration);
					pw.println("Average Video Bitrate : " + avgBr);
					pw.flush();
					reprcount.clear();
					time.clear();
					repBr.clear();
					repId.clear();
					buffer.clear();
				}
			}
		}
	}

	
	
	
	
	