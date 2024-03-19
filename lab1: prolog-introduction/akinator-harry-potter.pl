% akinator-harry-potter.pl

% Predicate main/0
% Entry point of the program that handles the recognition of a problem.
% True if a character is successfully recognized and outputted, false otherwise.
main :-
    retractall(no_any_questions(_)),
    retractall(asked(_,_)),
    character(Character),
    !,
    nl,
    write('The character is '), write(Character), write(.), nl.
main :-
    nl,
    write('The character cannot be recognized.'), nl.

% Predicate question(+Category:atom, -Response:atom)
% Queries the user with a yes/no question related to the given category and returns their response.
% Parameters:
%   - Category: The category of the question (e.g., 'wizard', 'not_negative').
%   - Response: The user's response ('y' for yes, 'n' for no).
% True if the user responds 'y' to the question for the given category.

question(wizard, Response) :- query('Is the character a wizard or witch?', Response).
question(not_negative, Response) :- query('Is this a non negative character?', Response).
question(attended_hogwarts, Response) :- query('Did the character attend Hogwarts School of Witchcraft and Wizardry?', Response).
question(gryffindor, Response) :- query('Is the character related to Gryffindor?', Response).
question(teacher, Response) :- query('Is the character was a teacher?', Response).
question(order_of_phoenix, Response) :- query('Is the character a member of the Second Order of the Phoenix?', Response).
question(malfoy_family, Response) :- query('Is the character associated with the Malfoy family?', Response).
question(worked_for_voldemort, Response) :- query('Did the character work for Voldemort?', Response).
question(woman, Response) :- query('Is this a female character?', Response).
question(harrys_friend, Responcse) :- query('Is this Harry`s friend?', Responcse).


% 1.
% Predicate character(+Name:atom)
% Checks if the character with the given name satisfies certain criteria.
% Parameters:
%   - Name: The name of the character to be checked.
% True if the all questions are true

character(harry_potter) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, y),
    question(teacher, n),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n),
    question(woman, n),
    question(harrys_friend, n).

% 2.
character(hermione_granger) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, y),
    question(teacher, n),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n),
    question(woman, y).

% 3.
character(ron_weasley) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, y),
    question(teacher, n),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n),
    question(woman, y),
    question(harrys_friend, y).

% 4.
character(albus_dumbledore) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, y),
    question(teacher, y),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 5.
character(severus_snape) :-
    question(wizard, y),
    question(not_negative, n),
    question(attended_hogwarts, y),
    question(gryffindor, n),
    question(teacher, y),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, y).

% 6.
character(voldemort) :-
    question(wizard, y),
    question(not_negative, n),
    question(attended_hogwarts, y),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, n),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 7.
character(draco_malfoy) :-
    question(wizard, y),
    question(not_negative, n),
    question(attended_hogwarts, y),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, n),
    question(malfoy_family, y),
    question(worked_for_voldemort, y).

% 8.
character(sirius_black) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, y),
    question(teacher, n),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 9.
character(luna_lovegood) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, n),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 10.
character(neville_longbottom) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, y),
    question(teacher, n),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 11.
character(minerva_mcgonagall) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, y),
    question(teacher, y),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 12.
character(remus_lupin) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, y),
    question(teacher, y),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 13.
character(dobby) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, n),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, n),
    question(malfoy_family, y),
    question(worked_for_voldemort, n).

% 14.
character(hagrid) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, y),
    question(teacher, y),
    question(order_of_phoenix, n),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 15.
character(viktor_krum) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, n),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, n),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 16.
character(draco_malfoy) :-
    question(wizard, y),
    question(not_negative, n),
    question(attended_hogwarts, y),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, n),
    question(malfoy_family, y),
    question(worked_for_voldemort, y).

% 17.
character(lucius_malfoy) :-
    question(wizard, y),
    question(not_negative, n),
    question(attended_hogwarts, y),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, n),
    question(malfoy_family, y),
    question(worked_for_voldemort, y).

% 18.
character(vernon_dursley) :-
    question(wizard, n),
    question(not_negative, n),
    question(attended_hogwarts, n),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, n),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 19.
character(mr_and_mrs_granger) :-
    question(wizard, n),
    question(not_negative, y),
    question(attended_hogwarts, n),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, n),
    question(malfoy_family, n),
    question(worked_for_voldemort, n).

% 20.
character(lily_potter) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n),
    question(woman, y).

% 21.
character(james_potter) :-
    question(wizard, y),
    question(not_negative, y),
    question(attended_hogwarts, y),
    question(gryffindor, n),
    question(teacher, n),
    question(order_of_phoenix, y),
    question(malfoy_family, n),
    question(worked_for_voldemort, n),
    question(woman, n).

% Predicate common_characters/0
% Checks if there are other characters in the database that satisfy the question.
% If there are other characters, retract any previous 'no_any_questions' assertions.
common_characters :-
    (character(Character1), character(Character2), Character1 \== Character2) ->
    retractall(no_any_questions(_)) % If there are other characters, retract any previous assertions of 'no_any_questions'.
    ;
    true.

% Predicate query(+Prompt:atom, -Response:atom)
% Queries the user with a yes/no prompt and returns their response.
% Parameters:
%   - Prompt: The prompt to be displayed to the user.
%   - Response: The user's response ('y' for yes, 'n' for no).
% True if the user's response matches the provided Response.
query(Prompt, Response) :-
    (   (asked(Prompt, Reply);
        no_any_questions(_))-> true
    ;   nl, write(Prompt), write(' (y/n)? '),
        read(X),(X = y -> Reply = y ; Reply = n),
        assert(no_any_questions(_)),
	assert(asked(Prompt, Reply)),
	common_characters
    ),
    asked(Prompt, Reply) -> Reply=Response;
    no_any_questions(_) -> true.