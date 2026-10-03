package Part2;

public record Pair (
        int a,
        int b
){
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair pair = (Pair) o;
        return a == pair.a && b == pair.b || a == pair.b && b == pair.a;
    }
}
