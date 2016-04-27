package ch.checkbit.sia.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeta on 16/01/16.
 */
public class CharismaQuests {

    private static  List<Quest> quests = new ArrayList<>();

    static {
        // 0
        int i = 0;
        Quest q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Watch for your intonation";
        q.description  = "Lower the intonation of your voice at the end of your sentences.";
        q.info = "";
        quests.add(q);

        // 1
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Think before you speak";
        q.description  = "Pause for two full seconds before you speak.";
        q.info = "";
        quests.add(q);

        // 2
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Less nodding";
        q.description  = "Reduce how quickly and how often you nod.";
        q.info = "";
        quests.add(q);

        // 3
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Be present";
        q.description  = "Try to remain present in all conversations by focusing your attention on the sensations in your toes for a second and then get back to focusing on the other person.";
        q.info = "";
        quests.add(q);

        // 4
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Transfer your responsibility to the universe";
        q.description  = "Practice \"responsibility transfer\". If you are concerned with the outcome of something today, consider that everything is in the hands of all-powerful entity (the universe) and at the end it decides the outcome.";
        q.info = "";
        quests.add(q);

        // 5
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Battle comparison discomfort via responsibility transfer";
        q.description  = "Try to catch yourself when making a comparison that causes discomfort and use the \"responsibility transfer\" technique to alleviate any internal discomfort it may cause.";
        q.info = "";
        quests.add(q);

        // 6
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Eye contact";
        q.description  = "Look people right in their eyes.";
        q.info = "";
        quests.add(q);

        // 7
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Listen to what people say";
        q.description  = "Let people speak about themselves and convince you how great they are. Ask them open answer questions about their hobbies, families, job, etc.";
        q.info = "";
        quests.add(q);

        // 8
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Fight the impostor syndrome";
        q.description  = "Focus on fighting the effects of the \"impostor syndrome\" Think about it positively, it is great motivation to improve yourself. Think about it as being common and most people experience it. Think about it as an insecurity of the intelligent people.";
        q.info = "";
        quests.add(q);

        // 9
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Alleviate discomfort";
        q.description  = "Try to stay aware of any physical discomfort and if it arises during an interaction, act promptly to alleviate or explain it.";
        q.info = "";
        quests.add(q);

        // 10
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Rationalize your negativity";
        q.description  = "Pay attention to the body language of the persons. If you feel negativity towards you, repeat to yourself that it is not you and create an external excuse to explain the person’s behaviour.";
        q.info = "";
        quests.add(q);

        // 11
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Use this magic trick to remember names";
        q.description  = "If you meet new people, try to remember their names by imagining something connected with their names in 6 seconds.";
        q.info = "";
        quests.add(q);

        // 12
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Try to remember names";
        q.description  = "If you learn new people with uncommon names, ask them about their name’s origin. If they have common names, repeat their names once.";
        q.info = "";
        quests.add(q);

        // 13
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Be aware of negativity bias";
        q.description  = "When your brain spins negative scenarios, remind yourself that you may not be getting an accurate perception of reality. Your brain might be following its negativity bias, playing up some elements more than others or omitting some positive entirely.";
        q.info = "";
        quests.add(q);

        // 14
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Label negative emotions";
        q.description  = "Assign a label to your negative experience: self-criticism, anxiety, anger, etc. Just naming what you are thinking and feeling can help neutralize it.";
        q.info = "";
        quests.add(q);

        // 15
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Turn off your mental chatter";
        q.description  = "Imagine your mental chatter as coming from a radio. Try to turn down the volume, or even just put the radio to the side and let it chatter away.";
        q.info = "";
        quests.add(q);

        // 16
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Imagine the worst-case scenario";
        q.description  = "Consider the worst-case outcome for you situation. Realize that whatever it is, you will survive.";
        q.info = "";
        quests.add(q);

        // 17
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Train \"cognitive reappraisal\"";
        q.description  = "If something annoying or bad happens, try to change beliefs. Imagine that it should have happened as it is, because the alternatives are worse or because it helped somebody that needed it more than you do.";
        q.info = "";
        quests.add(q);


        // 18
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Rewrite reality";
        q.description  = "Practice \"rewrite reality\" whenever you are impatient or feel anger. Invent alternative explanation that favours the current situation.";
        q.info = "";
        quests.add(q);

        // 19
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Be a superhero today";
        q.description  = "Sit down and write a page of a diary of daydreaming about yourself. For example, the day of the superhero-you or the day of the Wimbledon-winner-you. This is especially helpful before big events or presentations.";
        q.info = "";
        quests.add(q);

        // 20
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Play the SILENCE GAME";
        q.description  = "In negotiations or arguments play the following game: if you encounter a moment of silence, try to resist it and remain silent for as long as possible.";
        q.info = "";
        quests.add(q);

        // 21
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Play the EYE CONTACT GAME";
        q.description  = "In negotiations or arguments play the following game: try to hold eye contact for as long as possible.";
        q.info = "";
        quests.add(q);

        // 22
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Play the CREEPER GAME";
        q.description  = "For the day, hold eye contacts longer than it is comfortable.";
        q.info = "";
        quests.add(q);

        // 23
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Play the PERSONAL SPACE GAME";
        q.description  = "Experiment with personal space. Move closer to people than you usually would in an elevator for example.";
        q.info = "";
        quests.add(q);

        // 24
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Overcome the fear of being social";
        q.description  = "Strike a conversation with a complete stranger.";
        q.info = "";
        quests.add(q);


        // 25
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Feel like a winner";
        q.description  = "Whenever during the day you need to feel confident, close your eyes and try to visualize a great triumph of yours. The more details the visualization includes, the bigger the effect.";
        q.info = "";
        quests.add(q);

        // 26
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Be a comedian for a day";
        q.description  = "Whenever during the day you want to be funny, close your eyes and imagine that you are a stand-up comedian and the people laugh on your jokes. More detailed visualization achieve better results.";
        q.info = "";
        quests.add(q);

        // 27
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Listen to uplifting beat";
        q.description  = "Before giving a presentation, while listening to an uplifting, energizing soundtrack, take your time and visualize that the presentation is going wonderful with all small details, like the audience smiling and applauding.";
        q.info = "";
        quests.add(q);

        // 28
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Visualize triumph";
        q.description  = "Before important meeting or before important email, visualize the triumph with much details like handshakes, smiles, etc.";
        q.info = "";
        quests.add(q);

        // 29
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Be grateful";
        q.description  = "To boost gratitude, throughout the day, focus on the little things that are physically present and be grateful for even the smallest one like a sun shining through a window.";
        q.info = "";
        quests.add(q);

        // 30
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Write the story of your life";
        q.description  = "Try to view your life through a third-person lense, writing a narrative about yourself. This will help you boost gratitude. Write a story of your life through the eyes of a third person. Focus on the positive aspects and be detailed.";
        q.info = "";
        quests.add(q);

        // 31
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Appreciate your strengths";
        q.description  = "If you feel annoyance, sweep through your body from head to toes and find three abilities you approve of and be grateful.";
        q.info = "";
        quests.add(q);

        // 32
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Find the good in your enemies";
        q.description  = "To boost goodwill, find two things you like about the person you want to feel goodwill toward.";
        q.info = "";
        quests.add(q);


        // 33
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Boost your warmth";
        q.description  = "In any interaction throughout the day, imagine the person you are speaking to, and all those around you as having invisible angel wings.";
        q.info = "";
        quests.add(q);


        // 34
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Practice self-compassion";
        q.description  = "Whenever you are having a hard time, list five ways that you already care for yourself. This will boost self-compassion.";
        q.info = "";
        quests.add(q);


        // 35
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Practice Metta";
        q.description  = "Practice the Metta technique: take a few deep breaths, imagine a very influential person (historical, real or sci fi) and see yourself through their eyes with warmth, kindness and compassion. Feel them giving you their complete acceptance and forgiveness.";
        q.info = "";
        quests.add(q);

        // 36
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Change your posture to change your mood";
        q.description  = "If you feel gloomy and depressed, try to influence your mood by changing your body posture. Jump up and down as if you won the lottery. Smile the biggest smile you have and wave your arms in the air.";
        q.info = "";
        quests.add(q);


        // 37
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Be a military general";
        q.description  = "For a boost in confidence and assertiveness, imagine to play the role of a military general - take wide stance. puff up your chest, broaden your shoulders, stand straight, put your arms behind your back.";
        q.info = "";
        quests.add(q);

        // 38
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Boost your energy";
        q.description  = "For boost in both energy and warmth, stand up, stretch your hands as high up as possible, inhale as much as you can, imagine your rib cage expanding - make the biggest smile you can and look upward, hold for a second and relax everything.";
        q.info = "";
        quests.add(q);

        // 39
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Improve your speaking";
        q.description  = "Concentrate today on your speaking. Try to speak less, more slowly, with pauses and drop your intonation at the end. While speak, try to avoid nodding!";
        q.info = "";
        quests.add(q);

        // 40
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Play the EXPANSION GAME";
        q.description  = "Play the game of taking as much space as possible.";
        q.info = "";
        quests.add(q);

        // 41
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Improve your handshake";
        q.description  = "Train your handshake.";
        q.info = "";
        quests.add(q);

        // 42
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Practice mirroring";
        q.description  = "Train mirroring and mimicking. Try to imitate people’s behaviour. But be careful! The other should not realize he/she is being imitated. But, be selective and mirror only what feels natural to you, vary the amplitude of the gesture and use lag time.";
        q.info = "";
        quests.add(q);

        // 43
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "It’s all about YOU";
        q.description  = "Whenever in conversation, try to NOT use \"I\" often. Instead, rewrite the sentence with \"you\".";
        q.info = "";
        quests.add(q);

        // 44
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Avoid interrupting";
        q.description  = "In a conversation, never interrupt the person speaking. Moreover, if you are being interrupt, let it be. Master listeners know one trick, they pause before answering. Your face should react before you start speaking.";
        q.info = "";
        quests.add(q);

        // 45
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Enjoy every compliment";
        q.description  = "Whenever you receive a compliment, try to show that you enjoy it and always say thank you. Better, compliment back.";
        q.info = "";
        quests.add(q);

        // 46
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Make someone a superstar";
        q.description  = "To make the person you are talking to feel important, imagine he/she is a superstar. This will adjust you body language accordingly.";
        q.info = "";
        quests.add(q);

        // 47
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Say goodbye in a proper way";
        q.description  = "Keep eye contact with the person you are interacting with for 3 full seconds at the end of your interaction.";
        q.info = "";
        quests.add(q);

        // 48
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Imagine a baby dragon with a cupcake...";
        q.description  = "In the conversations during the day, always check if your eyes feel tense. If so, try a quick visualization of baby dragon giving you cupcake.";
        q.info = "";
        quests.add(q);

        // 49
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Play the GORILLA GAME";
        q.description  = "When you are out in a crowded environment, practice getting people to move aside for you. Visualize what a big gorilla would look like charging down the street and adopt the corresponding body language.";
        q.info = "";
        quests.add(q);

        // 50
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Ask for favors";
        q.description  = "Asking people you think that do not like you for favors can help them starting liking you.";
        q.info = "";
        quests.add(q);

        // 51
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Appreciate the work of others";
        q.description  = "Praise three people today for something they are doing good.";
        q.info = "";
        quests.add(q);

        // 52
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Depersonalize critic";
        q.description  = "Whenever you give critic, try to depersonalize it. For example with simple story, that happened to you or using another person as an example.";
        q.info = "";
        quests.add(q);

        // 53
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Share success";
        q.description  = "Try to give the people a sense of ownership for your success. Transfer the glory by pointing out their help.";
        q.info = "";
        quests.add(q);

        // 54
        q = new Quest(); q.questId = i++; q.icon = "quest_" + q.questId;
        q.title = "Spot unconscious bias and stereotyping";
        q.description  = "Look at the world with wide-open eyes and find an instance of unconscious bias or stereotyping. It could be anything - an advertisement, a job posting, a product, an interaction at work or at home.";
        q.info = "";
        quests.add(q);


    }

    public static List<Quest> getQuests() {
        return quests;
    }
}
