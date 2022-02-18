from csp import Constraint, CSP
from typing import Dict, List, Optional


class GeqConstraint(Constraint[int, int]):
    def __init__(self, num1: int, num2: int) -> None:
        super().__init__([num1, num2])
        self.num1: int = num1
        self.num2: int = num2

    def isSatisfied(self, assignment: Dict[int, int]) -> bool:
        if self.num1 not in assignment or self.num2 not in assignment:
            return True
        val1 = assignment[self.num1]
        val2 = assignment[self.num2]
        return val1 >= val2


class AbsEqOneConstraint(Constraint[int, int]):
    def __init__(self, num1: int, num2: int) -> None:
        super().__init__([num1, num2])
        self.num1: int = num1
        self.num2: int = num2

    def isSatisfied(self, assignment: Dict[int, int]) -> bool:
        if self.num1 not in assignment or self.num2 not in assignment:
            return True
        val1 = assignment[self.num1]
        val2 = assignment[self.num2]
        abs_val = abs(val1 - val2)
        return abs_val == 1


class NotEqConstraint(Constraint[int, int]):
    def __init__(self, num1: int, num2: int) -> None:
        super().__init__([num1, num2])
        self.num1: int = num1
        self.num2: int = num2

    def isSatisfied(self, assignment: Dict[int, int]) -> bool:
        if self.num1 not in assignment or self.num2 not in assignment:
            return True
        val1 = assignment[self.num1]
        val2 = assignment[self.num2]
        return val1 != val2


class AbsOddConstraint(Constraint[int, int]):
    def __init__(self, num1: int, num2: int) -> None:
        super().__init__([num1, num2])
        self.num1: int = num1
        self.num2: int = num2

    def isSatisfied(self, assignment: Dict[int, int]) -> bool:
        if self.num1 not in assignment or self.num2 not in assignment:
            return True
        val1 = assignment[self.num1]
        val2 = assignment[self.num2]
        abs_val = abs(val1 - val2)
        return abs_val % 2 != 0


class LesConstraint(Constraint[int, int]):
    def __init__(self, num1: int, num2: int) -> None:
        super().__init__([num1, num2])
        self.num1: int = num1
        self.num2: int = num2

    def isSatisfied(self, assignment: Dict[int, int]) -> bool:
        if self.num1 not in assignment or self.num2 not in assignment:
            return True
        val1 = assignment[self.num1]
        val2 = assignment[self.num2]
        return val1 < val2


class AbsEvConstraint(Constraint[int, int]):
    def __init__(self, num1: int, num2: int) -> None:
        super().__init__([num1, num2])
        self.num1: int = num1
        self.num2: int = num2

    def isSatisfied(self, assignment: Dict[int, int]) -> bool:
        if self.num1 not in assignment or self.num2 not in assignment:
            return True
        val1 = assignment[self.num1]
        val2 = assignment[self.num2]
        abs_val = abs(val1 - val2)
        return abs_val % 2 == 0


class LTMinOneConstraint(Constraint[int, int]):
    def __init__(self, num1: int, num2: int) -> None:
        super().__init__([num1, num2])
        self.num1: int = num1
        self.num2: int = num2

    def isSatisfied(self, assignment: Dict[int, int]) -> bool:
        if self.num1 not in assignment or self.num2 not in assignment:
            return True
        val1 = assignment[self.num1]
        val2 = assignment[self.num2]
        return val1 < (val2 - 1)


class NotEqMinTwoConstraint(Constraint[int, int]):
    def __init__(self, num1: int, num2: int) -> None:
        super().__init__([num1, num2])
        self.num1: int = num1
        self.num2: int = num2

    def isSatisfied(self, assignment: Dict[int, int]) -> bool:
        if self.num1 not in assignment or self.num2 not in assignment:
            return True
        val1 = assignment[self.num1]
        val2 = assignment[self.num2]
        return val1 != (val2 - 2)


if __name__ == "__main__":
    variables: List[str] = ["F", "H", "E", "D", "C", "A", "G", "B"]
    domains: Dict[str, List[int]] = {}
    for variable in variables:
        domains[variable] = [1, 2, 3, 4]

    csp: CSP[str, int] = CSP(variables, domains)

    csp.add_constraint(GeqConstraint("A", "G")) #
    csp.add_constraint(LesConstraint("A", "H"))
    csp.add_constraint(AbsEqOneConstraint("F", "B")) #
    csp.add_constraint(LesConstraint("G", "H"))
    csp.add_constraint(AbsEqOneConstraint("G", "C"))
    csp.add_constraint(AbsEvConstraint("H", "C"))
    csp.add_constraint(NotEqConstraint("H", "D"))
    csp.add_constraint(GeqConstraint("D", "G"))
    csp.add_constraint(NotEqConstraint("D", "C"))
    csp.add_constraint(NotEqConstraint("E", "C"))
    csp.add_constraint(LTMinOneConstraint("E", "D")) #
    csp.add_constraint(NotEqMinTwoConstraint("E", "H"))
    csp.add_constraint(NotEqConstraint("G", "F"))
    csp.add_constraint(NotEqConstraint("H", "F"))
    csp.add_constraint(NotEqConstraint("C", "F")) #
    csp.add_constraint(NotEqConstraint("D", "F"))
    csp.add_constraint(AbsOddConstraint("E", "F"))

    ret_val: Optional[Dict[str, int]] = csp.DFS()
    if ret_val is None:
        print("No solution found!")
    else:
        print("Solution is" + " " + str(ret_val))
