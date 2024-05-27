% Example graph for task 7.1
edge(a, b).
edge(b, c).
edge(c, d).
edge(d, e).
edge(a, c).
edge(c, a).

% Example graph for task 7.2
edge_undirected(a, b).
edge_undirected(b, c).
edge_undirected(c, d).
edge_undirected(d, e).
edge_undirected(a, c).

% Example graph for task 7.3
edge_directed(a, b).
edge_directed(b, c).
edge_directed(c, d).
edge_directed(a, d).

% Example graph for task 7.4
edge_tree(a, b).
edge_tree(b, c).
edge_tree(c, d).
edge_tree(d, e).

% Example graph for task 7.5
edge_weighted(a, b, 1).
edge_weighted(b, c, 2).
edge_weighted(c, d, 3).
edge_weighted(a, d, 10).
edge_weighted(d, e, -5).

% 1. Amount of paths of length N
% count_paths_length_n(+Start:atom, +End:atom, +N:integer, -Count:integer)
% Counts the number of paths of length N from Start to End in a mixed graph.
count_paths_length_n(Start, End, N, Count) :-
    findall(Path, path_length_n(Start, End, N, Path), Paths),
    length(Paths, Count).

% path_length_n(+Start:atom, +End:atom, +N:integer, -Path:list)
% Finds a path of length N from Start to End.
path_length_n(Start, End, 0, [Start]) :- Start = End.
path_length_n(Start, End, N, [Start|Path]) :-
    N > 0,
    edge(Start, Next),
    N1 is N - 1,
    path_length_n(Next, End, N1, Path).

% 2. Maximum independent set in an undirected graph
% max_independent_set(+Graph:list, -Set:list)
% Finds the maximum independent set of vertices in an undirected graph.
max_independent_set(Graph, Set) :-
    findall(Vertex, member(Vertex, Graph), Vertices),
    find_independent_set(Vertices, [], Set).

% find_independent_set(+Vertices:list, +CurrentSet:list, -Set:list)
% Helper predicate to find the independent set.
find_independent_set([], Set, Set).
find_independent_set([V|Vertices], CurrentSet, Set) :-
    (independent(V, CurrentSet) ->
        find_independent_set(Vertices, [V|CurrentSet], Set)
    ; find_independent_set(Vertices, CurrentSet, Set)).

% independent(+V:atom, +Set:list)
% Checks if vertex V is independent of the vertices in Set.
independent(_, []).
independent(V, [U|Set]) :-
    \+ edge_undirected(V, U),
    independent(V, Set).

% 3. Topological sort of a directed graph (weakly connected)
% topological_sort(+Graph:list, -Sorted:list)
% Performs a topological sort on a directed weakly connected graph.
topological_sort(Graph, Sorted) :-
    findall(Vertex, member(Vertex, Graph), Vertices),
    topological_sort(Vertices, [], Sorted).

% topological_sort(+Vertices:list, +CurrentSorted:list, -Sorted:list)
% Helper predicate to perform the topological sort.
topological_sort([], Sorted, Sorted).
topological_sort(Vertices, CurrentSorted, Sorted) :-
    select(Vertex, Vertices, Rest),
    \+ (edge_directed(Vertex, _)),
    topological_sort(Rest, [Vertex|CurrentSorted], Sorted).

% 4. Check if a graph is a tree
% is_tree(+Graph:list)
% Checks if the graph is a tree.
is_tree(Graph) :-
    findall(Vertex, member(Vertex, Graph), Vertices),
    length(Vertices, V),
    length(Graph, E),
    E =:= V - 1,
    connected(Graph).

% connected(+Graph:list)
% Checks if the graph is connected.
connected(Graph) :-
    findall(Vertex, member(Vertex, Graph), [Start|_]),
    reachable(Start, Graph, Reachable),
    length(Reachable, V),
    length(Graph, E),
    E =:= V - 1.

% reachable(+Start:atom, +Graph:list, -Reachable:list)
% Finds all vertices reachable from Start.
reachable(Start, Graph, Reachable) :-
    reachable([Start], Graph, [], Reachable).

% reachable(+Queue:list, +Graph:list, +Visited:list, -Reachable:list)
% Helper predicate to find all reachable vertices.
reachable([], _, Reachable, Reachable).
reachable([V|Queue], Graph, Visited, Reachable) :-
    \+ member(V, Visited),
    findall(U, (member(edge_tree(V, U), Graph); member(edge_tree(U, V), Graph)), Neighbors),
    append(Queue, Neighbors, NewQueue),
    reachable(NewQueue, Graph, [V|Visited], Reachable).
reachable([_|Queue], Graph, Visited, Reachable) :-
    reachable(Queue, Graph, Visited, Reachable).

% 5. Bellman-Ford algorithm for finding shortest paths
% bellman_ford(+Graph:list, +Start:atom, -Distances:list)
% Finds the shortest paths from Start to all other vertices using the Bellman-Ford algorithm.
bellman_ford(Graph, Start, Distances) :-
    findall(Vertex, member(Vertex, Graph), Vertices),
    initialize_distances(Vertices, Start, InitialDistances),
    relax_edges(Vertices, Graph, InitialDistances, Distances).

% initialize_distances(+Vertices:list, +Start:atom, -Distances:list)
% Initializes the distances from Start to all vertices.
initialize_distances([], _, []).
initialize_distances([V|Vertices], Start, [distance(V, 0)|Distances]) :-
    V = Start,
    initialize_distances(Vertices, Start, Distances).
initialize_distances([V|Vertices], Start, [distance(V, inf)|Distances]) :-
    V \= Start,
    initialize_distances(Vertices, Start, Distances).

% relax_edges(+Vertices:list, +Graph:list, +CurrentDistances:list, -FinalDistances:list)
% Relaxes the edges to find the shortest paths.
relax_edges([], _, Distances, Distances).
relax_edges([_|Vertices], Graph, CurrentDistances, FinalDistances) :-
    relax_all_edges(Graph, CurrentDistances, NewDistances),
    relax_edges(Vertices, Graph, NewDistances, FinalDistances).

% relax_all_edges(+Edges:list, +CurrentDistances:list, -NewDistances:list)
% Relaxes all edges in the graph.
relax_all_edges([], Distances, Distances).
relax_all_edges([edge_weighted(U, V, W)|Edges], CurrentDistances, NewDistances) :-
    relax_edge(U, V, W, CurrentDistances, TempDistances),
    relax_all_edges(Edges, TempDistances, NewDistances).

% relax_edge(+U:atom, +V:atom, +W:number, +CurrentDistances:list, -NewDistances:list)
% Relaxes a single edge in the graph.
relax_edge(U, V, W, CurrentDistances, NewDistances) :-
    member(distance(U, DU), CurrentDistances),
    member(distance(V, DV), CurrentDistances),
    (DU + W < DV ->
        update_distance(V, DU + W, CurrentDistances, NewDistances)
    ; NewDistances = CurrentDistances).

% update_distance(+V:atom, +NewD:number, +CurrentDistances:list, -NewDistances:list)
% Updates the distance to vertex V.
update_distance(_, _, [], []).
update_distance(V, NewD, [distance(V, _)|Distances], [distance(V, NewD)|NewDistances]) :-
    update_distance(V, NewD, Distances, NewDistances).
update_distance(V, NewD, [D|Distances], [D|NewDistances]) :-
    update_distance(V, NewD, Distances, NewDistances).

% task 7 example queries:
% Количество путей длины 3
% ?- count_paths_length_n(a, d, 3, Count).

% Максимальное независимое множество
% ?- max_independent_set([a, b, c, d, e], Set).

% Топологическая сортировка
% ?- topological_sort([a, b, c, d], Sorted).

% Проверка, является ли граф деревом
% ?- is_tree([a, b, c, d, e]).

% Алгоритм Беллмана-Форда
% ?- bellman_ford([a, b, c, d, e], a, Distances).


% task 8

% run_all
% Unified predicate to call all necessary predicates and display their results.
run_all :-
    write('Задание 1: Получить все возможные помеченные неориентированные (p,q) графы'), nl,
    labeled_graphs(3, 2, Graphs1),
    write_labeled_graphs(Graphs1), nl, nl,

    write('Задание 2: Получить все возможные помеченные неориентированные графы порядка p'), nl,
    labeled_graphs_order(3, Graphs2),
    write_labeled_graphs_order(Graphs2), nl, nl,

    write('Задание 3: Построить все эйлеровы графы из 8 вершин и 12 ребер'), nl,
    eulerian_graphs(Graphs3),
    write_eulerian_graphs(Graphs3), nl.

% labeled_graphs(+P:integer, +Q:integer, -Graphs:list)
% Generates all possible labeled undirected (p,q) graphs.
labeled_graphs(P, Q, Graphs) :-
    findall(V, between(1, P, V), Vertices),
    findall(E, (member(V1, Vertices), member(V2, Vertices), V1 < V2), Edges),
    subset(Edges, Q, Subset),
    Graphs = [graph(Vertices, Subset)].

% subset(+List:list, +Q:integer, -Subset:list)
% Generates all subsets of a given size Q from the list.
subset([], 0, []).
subset([H|T], Q, [H|Subset]) :-
    Q > 0,
    Q1 is Q - 1,
    subset(T, Q1, Subset).
subset([_|T], Q, Subset) :-
    Q >= 0,
    subset(T, Q, Subset).

% labeled_graphs_order(+P:integer, -Graphs:list)
% Generates all possible labeled undirected graphs of order P.
labeled_graphs_order(P, Graphs) :-
    findall(V, between(1, P, V), Vertices),
    findall(E, (member(V1, Vertices), member(V2, Vertices), V1 < V2), Edges),
    subsets(Edges, Subsets),
    maplist(graph_edges(Vertices), Subsets, Graphs).

% subsets(+List:list, -Subsets:list)
% Generates all subsets of a given list.
subsets([], [[]]).
subsets([H|T], Subsets) :-
    subsets(T, SubsetsT),
    maplist(add_element(H), SubsetsT, SubsetsH),
    append(SubsetsT, SubsetsH, Subsets).

% add_element(+Element:term, +List:list, -NewList:list)
% Adds an element to the front of a list.
add_element(X, S, [X|S]).

% graph_edges(+Vertices:list, +Edges:list, -Graph:term)
% Creates a graph from vertices and edges.
graph_edges(Vertices, Edges, graph(Vertices, Edges)).

% eulerian_graphs(-Graphs:list)
% Generates all Eulerian graphs with 8 vertices and 12 edges.
eulerian_graphs(Graphs) :-
    Vertices = [1, 2, 3, 4, 5, 6, 7, 8],
    findall(E, (member(V1, Vertices), member(V2, Vertices), V1 < V2), Edges),
    findall(Graph, (subset(Edges, 12, Subset), is_eulerian(Vertices, Subset), Graph = graph(Vertices, Subset)), Graphs).

% is_eulerian(+Vertices:list, +Edges:list)
% Checks if a graph is Eulerian.
is_eulerian(Vertices, Edges) :-
    connected(Vertices, Edges),
    maplist(even_degree(Edges), Vertices).

% connected(+Vertices:list, +Edges:list)
% Checks if a graph is connected.
connected(Vertices, Edges) :-
    member(V, Vertices),
    reachable(V, Vertices, Edges, Visited),
    length(Visited, N),
    length(Vertices, N).

% reachable(+Vertex:term, +Vertices:list, +Edges:list, -Visited:list)
% Checks if all vertices are reachable from a given vertex.
reachable(_, [], _, []).
reachable(V, [V|Vs], Edges, [V|Visited]) :-
    reachable(V, Vs, Edges, Visited).
reachable(V, [W|Vs], Edges, Visited) :-
    member(edge(V, W), Edges),
    \+ member(W, Visited),
    reachable(W, Vs, Edges, Visited).

% even_degree(+Edges:list, +Vertex:term)
% Checks if a vertex has an even degree.
even_degree(Edges, V) :-
    findall(W, (member(edge(V, W), Edges); member(edge(W, V), Edges)), Neighbors),
    length(Neighbors, Degree),
    0 is Degree mod 2.

% write_labeled_graphs(+Graphs:list)
% Writes labeled undirected (p,q) graphs.
write_labeled_graphs([]).
write_labeled_graphs([graph(Vertices, Edges)|Graphs]) :-
    write('Граф: '), write(Vertices), write(', '), write(Edges), nl,
    write_labeled_graphs(Graphs).

% write_labeled_graphs_order(+Graphs:list)
% Writes labeled undirected graphs of order p.
write_labeled_graphs_order([]).
write_labeled_graphs_order([graph(Vertices, Edges)|Graphs]) :-
    write('Граф: '), write(Vertices), write(', '), write(Edges), nl,
    write_labeled_graphs_order(Graphs).

% write_eulerian_graphs(+Graphs:list)
% Writes Eulerian graphs.
write_eulerian_graphs([]).
write_eulerian_graphs([graph(Vertices, Edges)|Graphs]) :-
    write('Эйлеров граф: '), write(Vertices), write(', '), write(Edges), nl,
    write_eulerian_graphs(Graphs).

