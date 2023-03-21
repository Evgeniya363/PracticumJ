
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class Laptop {
        String brand;
        String model;
        String processor;
        int ramSize;
        String discType;
        int diskSize;
        String videoAdapter;
        float displaySize;
        String operatingSystem;
        float price;

        Laptop() {
                this.brand = "ASUS";
                this.model = "";
                this.processor = "i5";
                this.ramSize = 8;
                this.discType = "HDD";
                this.diskSize = 500;
                this.videoAdapter = "NVIDIA";
                this.displaySize = 15.6f;
                this.operatingSystem = "Linux";
                this.price = 52000;
        }

        Laptop(String brand, String model, String processor, int ramSize, String discType, int diskSize,
                        String videoAdapter, float displaySize, String operatingSystem, float price) {

                this.brand = brand;
                this.model = model;
                this.processor = processor;
                this.ramSize = ramSize;
                this.discType = discType;
                this.diskSize = diskSize;
                this.videoAdapter = videoAdapter;
                this.displaySize = displaySize;
                this.operatingSystem = operatingSystem;
                this.price = price;
        }

        Laptop(Map<String, String> hMap) {
                Iterator<Entry<String, String>> iterator = hMap.entrySet().iterator();
                while (iterator.hasNext()) {
                        var h = iterator.next();
                        String key = h.getKey();
                        String value = h.getValue();
                        System.out.println(key + value);
                        try {
                                Field field = Laptop.class.getDeclaredField(h.getKey());
                                String type = field.getGenericType().toString();
                                // System.out.println(type + "---");
                                if (type.contains("String"))
                                        field.set(this, value);
                                else if (type.contains("float"))
                                        field.set(this, Float.parseFloat(value));
                                else if (type.contains("int"))
                                        field.set(this, Integer.parseInt(value));
                                else {
                                        System.out.printf("Error: unknown type %s. Criterion removed\n", type);
                                        iterator.remove();
                                }
                        } catch (Exception e) {
                                System.out.println(e.getMessage());
                                System.out.println("Error! Criterion removed");
                                iterator.remove();
                        }
                }
        }

        boolean moreThan(Laptop lpt) {

                for (Field f : Laptop.class.getDeclaredFields()) {
                        Object o1 = null;
                        Object o2 = null;
                        try {
                                o1 = f.get(this);
                                o2 = f.get(lpt);
                        } catch (Exception e) {
                                System.out.println("Error!");
                        }
                        if (o1 == null || o2 == null)
                                continue;
                        String type = f.getGenericType().toString();
                        if (type.contains("String")) {
                                if (!(((String) o1).toLowerCase().contains(((String) o2).toLowerCase())))
                                        return false;
                        } else if (type.contains("float")) {

                                if ((float) o1 < (float) o2)
                                        return false;
                        } else if (type.contains("int")) {

                                if ((int) o1 < (int) o2)
                                        return false;
                        }

                }

                return true;
        }

        @Override
        public boolean equals(Object object) {
                Laptop obj = (Laptop) object;
                return obj.brand.equals(brand) && obj.model.equals(model) && obj.processor.equals(processor)
                                && obj.ramSize == ramSize && obj.discType.equals(discType) && obj.diskSize == diskSize
                                && obj.videoAdapter.equals(videoAdapter) && obj.displaySize == displaySize
                                && obj.operatingSystem.equals(operatingSystem) && obj.price == price;
        }

        @Override
        public String toString() {
                return String.format("%10s %30s %18s %5d %5s %4d %35s %2.1f %15s %7.2f", brand, model, processor,
                                ramSize, discType, diskSize, videoAdapter, displaySize, operatingSystem, price);
        }

        @Override
        public int hashCode() {
                return Objects.hash();
        }

}
