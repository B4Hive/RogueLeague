
# RogueLeague Project

1) Intro:

    A turn-based single player game made with the goal of "simulating" the experience of a MOBA (multiplayer online battle arena).

    Made fully on Java, using Swing as engine for GUI.

    Obviously not a MOBA game, since it'll be a single player game.

    Closest category for this would likely be a made up category, a Singleplayer offline battle arena, so SOBA (feeling hungry yet?).



2) Gameplay Plans:



    My plans (that may change while I develop it) are to make the gameplay function as following:

    The player selects a character from the roster (or makes a character, I'm still not fully sure about this part).

    Said character spawns into a corner of the arena (player base).

    A single AI enemy spawns on the oposite corner of the arena (enemy base).

    A few objectives (towers) are spawn in between the 2 bases.

    Those objectives will be considered "enemies" for both the player and it's AI enemy.

    Defeating said objectives will grant the one who defeated them a new ally.

    Once defeated, the objectives will respawn after a set amount of turns.

    If the player or one of it's AI allies find an enemy AI, they'll enter combat.

    After losing a fight a character will be inactive for a set amount of turns before respawning at their respective base.

    The combat and movement will all be turn based, with the player having the first action.

    Each enemy will enter the action order in the same order of their first spawn.

    At each action, the player must choose an action between moving, attacking and using an ability.

    Each ability is a set of combined effects, including always at least one instant effect and one lasting effect.

    Instant effects are those that just take effect in the turn they are used.

    Lasting effects are those that apply a repeating effect on the target for a set amount of turns.

    The mandatory lasting effect for every ability is the ability's cooldown.

    When a character deals the last hit on a combat (even if against an objective) they'll gain experience.

    Said experience will, at specific thresholds, increase the character's combat capabilities and status.

    The objective of the main game mode is to gather enough allies and group them at the enemy base for a set amount of turns.

    Each base has the special effect of healing it's corresponding characters.

    Enemy/Ally AI will move semi-randomly towards the Player/Enemy base (one step towards the base every 5 turns).

    When ally/enemy character has it's health points reduced to a specific point, they'll attempt to escape combat going towards their base.



3) Program Structure:

    Engine Class:

        Manage the gameplay by getting the player's inputs and calling the required methods to return the results of said action.

    Screen Class:

        Display the current state of the arena, with the character's positions, information (abilities) and status.

        Read the player's inputs directly and deliver them to the engine, as well as printing the results on screen.

    Map Class:

        Stores the character's positions on the map, as well as the rest of the map's layout.

        The necessary methods for movement are on this class.

    Character Class:

        Has the character's Status (HP,MP, XP, LV, etc), a list of the character's abilities and a list of the effects currently active.

        The necessary methods for combat are on this class.

        The secondary objectives are considered characters.

    Ability Class:

        Stores a list of every character's abilities, as well as their respective effects.

        Has the necessary methods for damage/healing/effect calcs.

        Has subclasses:

            Damage: meant to reduce an enemy's health points;

            Healing: meant to restore an ally's health points;

            Debuff: meant to apply a negative effect to a enemy;

            Buff: meant to apply a positive effect to a ally;

    Effect Class:

        Stores the types of effects.

        Has the methods for applying, removing and triggering each type of effect.

        Has subclasses:

            DoT: damage over time;

            HoT: healing over time;

            Enhance: increase specific stat;

            Diminish: decrease specific stat;

            Displace: forces the target's movement;

            Stun: prevents the target's movement;

            Shield: prevents damage;

            Wound/Inhibit/Cripple/Injury: prevents healing;

    Data Class:

        Handles the management for the game data, such as loading and saving the data on external files.



4) Versioning:

    Plan:

    0.0) Planning Done;

    0.1) Draft for the main classes;

    0.2) Map Class:

        0.2.1) Map Generation and Info Getters in order to print it;

        0.2.2) Movement with template characters;

        0.2.3) Colision effect with a boolean return in order to facilitate future features;

    0.3) Effect Class: Including the Effect types (subclasses) draft;

        0.3.1) Filling in the required methods for each effect type including duration settings;

    0.4) Abilities Class: Drafting the Ability types (subclasses); Filling out the required methods for skill casts/calcs;

        0.4.1) Making the first template Abilities (Basic X : X -> Effect Subclass + Instant Effects)

    0.5) Character Class: Drafting the Template Character with Basic Abilities;

        0.5.1) Vinculating the Character's commands with the Map/Ability commands

            Ability Targeting, Character Movement, Colision Action, etc;

    0.6) Engine Class: Making a functional "engine" in order to run further tests on game functionalities and interactions between classes;

        0.6.x) Improving and polishing each of the previous implemented content;

    0.7) Data Class: Setting methods to extract and import data from game to files;

        0.7.x) Finishing each of the Class exports;

    0.8) Screen Class: Putting everything together on Swing, Designing the screens and overall layout of GUI;

        0.8.1) Game Screen;

        0.8.2) Mapping Controls;

        0.8.3) Character Info GUI;

        0.8.4) Main Menu;

        0.8.5) In Game Options;

    0.9) Testing and Refining:

        0.9.x) New subversion for each improvement/fix until the game is playable

        0.9.x) Character creation

    1.0) Project Finished! (now maintain the updates of new ideas)
