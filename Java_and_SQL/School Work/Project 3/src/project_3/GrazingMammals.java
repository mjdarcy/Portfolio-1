//  GrazingMammals.java
interface RuminantTester {
    // Add your code here
}

abstract class Mammal {
    // Add your code here
}

abstract class GrazingMammal  extends Mammal implements RuminantTester {
    // Add your solution here
}

abstract class Ruminant extends GrazingMammal   {
    // Add your code here
}

class Cow extends Ruminant {
}

class Goat extends Ruminant {
}

class Horse extends GrazingMammal {
}

public class GrazingMammals {
    // Add your code here
}