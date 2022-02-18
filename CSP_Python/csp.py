from abc import ABC, abstractmethod
from typing import TypeVar, Generic, List, Dict, Optional

V = TypeVar('V')
D = TypeVar('D')



class Constraint(Generic[V, D], ABC):

    def __init__(self, variables: List[V]) -> None:
        self.variables = variables

    @abstractmethod
    def isSatisfied(self, assignment: Dict[V, D]) -> bool:
        ...


class CSP(Generic[V, D]):

    def __init__(self, variables: List[V], domains: Dict[V, List[D]]) -> None:
        self.variables: List[V] = variables
        self.domains: Dict[V, D] = domains
        self.constraints: Dict[V, List[Constraint[V, D]]] = {}

        for variable in self.variables:
            self.constraints[variable] = []
            if variable not in self.domains:
                raise LookupError("Variable is not in domains")

    def add_constraint(self, constraint: Constraint[V, D]) -> None:
        for variable in constraint.variables:
            if variable not in self.variables:
                raise LookupError
            else:
                self.constraints[variable].append(constraint)

    def consistentCheck(self, variable: V, assignment: Dict[V, D]) -> bool:
        for constraint in self.constraints[variable]:
            if not constraint.isSatisfied(assignment):
                print("Fail" + " " + str(assignment))
                return False
        return True

    def DFS(self, assignment: Dict[V, D] = {}) -> Optional[Dict[V, D]]:
        if len(assignment) == len(self.variables):
            return assignment

        unass: List[V] = [v for v in self.variables if v not in assignment]

        first_var: V = unass[0]

        for val in self.domains[first_var]:
            loc_ass = assignment.copy()
            loc_ass[first_var] = val
            if self.consistentCheck(first_var, loc_ass):
                ret_val: Optional[Dict[V, D]] = self.DFS(loc_ass)
                if ret_val is not None:
                    return ret_val
        return None

