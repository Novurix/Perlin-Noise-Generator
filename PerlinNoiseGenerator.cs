using System;

namespace PerlinNoise
{
    public class PerlinNoiseGenerator
    {
        static float[] area;

        public static void Main(string[] args) {
            generateNoise(10);
        }

        static void generateNoise(int scale) {
            int areaIndex = 0;
            area = new float[scale * scale];

            for (int y = 0; y < scale; y++)
            {
                for (int x = 0; x < scale; x++) {
                    Random random = new Random();

                    float randomIndex = random.Next(0, 10);
                    area[areaIndex] = randomIndex / 10;

                    try {
                        float preMadeHeight = area[areaIndex - scale];

                        Random randOp = new Random();
                        int randomOperator = randOp.Next(1, 3);
                        float newHeight = 0;

                        Random randomHeight = new Random();
                        if (randomOperator == 1) newHeight = preMadeHeight - (float)(randomHeight.Next(0, 5))/10;
                        else newHeight = preMadeHeight + (float)(randomHeight.Next(0, 5))/10;

                        area[areaIndex] = newHeight;
                    }
                    catch (Exception e) {
                        Console.WriteLine("area[areaIndex-scale] does not exist or it is out of bounds of the <area> array");
                    }

                    areaIndex++;
                }
            }
            int mappingIndex = 0;
            for (int y = 0; y < scale; y++)
            {
                string perlinIndexMapping = "";
                for (int x = 0; x < scale; x++)
                {
                    if (area[mappingIndex] < 0) area[mappingIndex] *= -1;

                    perlinIndexMapping += area[mappingIndex].ToString("0.0") + " ";
                    mappingIndex++;
                }

                Console.WriteLine(perlinIndexMapping);
            }
        }
    }
}
