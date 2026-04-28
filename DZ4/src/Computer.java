public class Computer {
    private final String cpu;
    private final int ram;
    private final int storage;
    private final String gpu;
    private final boolean ssd;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.ssd = builder.ssd;
    }

    @Override
    public String toString() {
        String storageType = ssd ? "SSD" : "HDD";
        String gpuStr = (gpu == null) ? "встроенная" : "'" + gpu + "'";
        return String.format("Computer{CPU='%s', RAM=%dGB, " +
                "Storage=%dGB %s, GPU=%s}", cpu, ram, storage,
                storageType, gpuStr);
    }

    public static class Builder {
        private final String cpu;
        private final int ram;
        private int storage = 256;
        private String gpu = null;
        private boolean ssd = true;

        private Builder(String cpu, int ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder ssd(boolean ssd) {
            this.ssd = ssd;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
    //игровой компьютер (Core i9, 32GB, 2000GB, RTX 4080, SSD) и офисный (Core i5, 16GB, дефолтные значения)
    public static void main(String[] args) {
        Computer gamingComputer = new Builder("Core i9", 32)
                .storage(2000)
                .gpu("RTX 4080")
                .ssd(true)
                .build();
        Computer officeComputer = new Builder("Core i5", 16)
                .build();
        System.out.println(gamingComputer);
        System.out.println(officeComputer);
    }
}
