package com.group7.swp391.drug_prevention.config;

import com.group7.swp391.drug_prevention.domain.AgeGroup;
import com.group7.swp391.drug_prevention.domain.OnlineCourse;
import com.group7.swp391.drug_prevention.domain.OnlineCourseAnswer;
import com.group7.swp391.drug_prevention.domain.OnlineCourseQuestion;
import com.group7.swp391.drug_prevention.repository.AgeGroupRepository;
import com.group7.swp391.drug_prevention.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final CourseRepository onlineCourseRepository;
    private final AgeGroupRepository ageGroupRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (onlineCourseRepository.count() > 0 || ageGroupRepository.count() > 0) {
            System.out.println("Data already exists. Skipping initialization.");
            return;
        }

        System.out.println("🚀 Initializing sample data for drug prevention courses...");

        // 1. Create Age Groups
        AgeGroup ageGroupAdolescents = new AgeGroup();
        ageGroupAdolescents.setName("Adolescents");
        ageGroupAdolescents.setAge("12-17"); // MODIFIED LINE

        AgeGroup ageGroupYoungAdults = new AgeGroup();
        ageGroupYoungAdults.setName("Young Adults");
        ageGroupYoungAdults.setAge("18-25"); // MODIFIED LINE

        ageGroupRepository.saveAll(Arrays.asList(ageGroupAdolescents, ageGroupYoungAdults));

        // List to hold all courses before saving
        List<OnlineCourse> courses = new ArrayList<>();

        // =================================================================
        // === COURSE 1: Understanding Addiction: The Brain Science      ===
        // =================================================================
        OnlineCourse course1 = new OnlineCourse();
        course1.setName("Understanding Addiction: The Brain Science");
        course1.setDescription("Learn how drugs affect the brain and lead to addiction. This course covers the basic neuroscience of the reward system.");
        course1.setImage("https://example.com/images/brain_science.png");
        course1.setVideoUrl("https://www.youtube.com/watch?v=NxH_W7_r_e8");
        course1.setStatus("Active");
        course1.setDuration(45); // in minutes
        course1.setCreatedAt(Instant.now());
        course1.setAgeGroup(ageGroupYoungAdults);
        course1.setQuestions(new ArrayList<>());

        // Q1.1
        OnlineCourseQuestion q1c1 = createQuestion("What is the primary neurotransmitter involved in the brain's reward pathway?", course1);
        q1c1.getAnswers().addAll(Arrays.asList(
                createAnswer("Serotonin", false, q1c1),
                createAnswer("Dopamine", true, q1c1),
                createAnswer("Adrenaline", false, q1c1),
                createAnswer("GABA", false, q1c1)
        ));
        course1.getQuestions().add(q1c1);

        // Q1.2
        OnlineCourseQuestion q2c1 = createQuestion("What is 'tolerance' in the context of drug use?", course1);
        q2c1.getAnswers().addAll(Arrays.asList(
                createAnswer("An allergic reaction to a drug.", false, q2c1),
                createAnswer("The enjoyable feeling a drug produces.", false, q2c1),
                createAnswer("The need to take more of a drug to get the same effect.", true, q2c1),
                createAnswer("The symptoms experienced when stopping drug use.", false, q2c1)
        ));
        course1.getQuestions().add(q2c1);

        // Q1.3
        OnlineCourseQuestion q3c1 = createQuestion("Which part of the brain, responsible for decision-making and impulse control, is significantly impaired by long-term addiction?", course1);
        q3c1.getAnswers().addAll(Arrays.asList(
                createAnswer("Cerebellum", false, q3c1),
                createAnswer("Brain Stem", false, q3c1),
                createAnswer("Hippocampus", false, q3c1),
                createAnswer("Prefrontal Cortex", true, q3c1)
        ));
        course1.getQuestions().add(q3c1);

        // Q1.4
        OnlineCourseQuestion q4c1 = createQuestion("Addiction is internationally recognized by medical professionals as a:", course1);
        q4c1.getAnswers().addAll(Arrays.asList(
                createAnswer("Chronic, treatable brain disease.", true, q4c1),
                createAnswer("Personal weakness or moral failing.", false, q4c1),
                createAnswer("Simple habit that is easy to break.", false, q4c1),
                createAnswer("Phase that people grow out of naturally.", false, q4c1)
        ));
        course1.getQuestions().add(q4c1);

        // Q1.5
        OnlineCourseQuestion q5c1 = createQuestion("What are 'withdrawal symptoms'?", course1);
        q5c1.getAnswers().addAll(Arrays.asList(
                createAnswer("The positive effects of the drug.", false, q5c1),
                createAnswer("The physical and mental effects that occur after stopping or reducing drug intake.", true, q5c1),
                createAnswer("A sign that the drug is not working.", false, q5c1),
                createAnswer("The desire to share drugs with friends.", false, q5c1)
        ));
        course1.getQuestions().add(q5c1);

        courses.add(course1);

        // =================================================================
        // === COURSE 2: Cannabis: Separating Fact from Fiction          ===
        // =================================================================
        OnlineCourse course2 = new OnlineCourse();
        course2.setName("Cannabis: Separating Fact from Fiction");
        course2.setDescription("An evidence-based look at cannabis, its effects on health, the risks of use, and the difference between THC and CBD.");
        course2.setImage("https://example.com/images/cannabis_facts.png");
        course2.setVideoUrl("https://www.youtube.com/watch?v=N3A626xrw3E");
        course2.setStatus("Active");
        course2.setDuration(35);
        course2.setCreatedAt(Instant.now());
        course2.setAgeGroup(ageGroupYoungAdults);
        course2.setQuestions(new ArrayList<>());

        // Q2.1
        OnlineCourseQuestion q1c2 = createQuestion("What is the main psychoactive compound in cannabis that produces the 'high'?", course2);
        q1c2.getAnswers().addAll(Arrays.asList(
                createAnswer("CBD (Cannabidiol)", false, q1c2),
                createAnswer("CBN (Cannabinol)", false, q1c2),
                createAnswer("THC (Tetrahydrocannabinol)", true, q1c2),
                createAnswer("CBC (Cannabichromene)", false, q1c2)
        ));
        course2.getQuestions().add(q1c2);

        // Q2.2
        OnlineCourseQuestion q2c2 = createQuestion("Can a person become addicted to cannabis?", course2);
        q2c2.getAnswers().addAll(Arrays.asList(
                createAnswer("No, it is a completely non-addictive plant.", false, q2c2),
                createAnswer("Yes, persistent use can lead to Cannabis Use Disorder.", true, q2c2),
                createAnswer("Only if it is smoked, not if it is eaten.", false, q2c2),
                createAnswer("Only people with pre-existing conditions get addicted.", false, q2c2)
        ));
        course2.getQuestions().add(q2c2);

        // Q2.3
        OnlineCourseQuestion q3c2 = createQuestion("How does heavy cannabis use impact the developing adolescent brain?", course2);
        q3c2.getAnswers().addAll(Arrays.asList(
                createAnswer("It can improve memory and attention span.", false, q3c2),
                createAnswer("It has no long-term effect on brain development.", false, q3c2),
                createAnswer("It can harm learning, memory, and impulse control.", true, q3c2),
                createAnswer("It only affects physical coordination, not the brain.", false, q3c2)
        ));
        course2.getQuestions().add(q3c2);

        // Q2.4
        OnlineCourseQuestion q4c2 = createQuestion("Are synthetic cannabinoids like 'K2' or 'Spice' a safe alternative to natural cannabis?", course2);
        q4c2.getAnswers().addAll(Arrays.asList(
                createAnswer("Yes, because they are legal in some areas.", false, q4c2),
                createAnswer("Yes, they are manufactured in safe labs.", false, q4c2),
                createAnswer("No, they are chemically different and can be dangerously unpredictable.", true, q4c2),
                createAnswer("They are exactly the same as natural cannabis.", false, q4c2)
        ));
        course2.getQuestions().add(q4c2);

        // Q2.5
        OnlineCourseQuestion q5c2 = createQuestion("What is the primary characteristic of CBD (Cannabidiol)?", course2);
        q5c2.getAnswers().addAll(Arrays.asList(
                createAnswer("It is the main component that causes the 'high'.", false, q5c2),
                createAnswer("It is non-psychoactive and is studied for medical use.", true, q5c2),
                createAnswer("It is more potent and dangerous than THC.", false, q5c2),
                createAnswer("It is only found in synthetic cannabis.", false, q5c2)
        ));
        course2.getQuestions().add(q5c2);

        courses.add(course2);

        // =================================================================
        // === COURSE 3: Peer Pressure: How to Say No                    ===
        // =================================================================
        OnlineCourse course3 = new OnlineCourse();
        course3.setName("Peer Pressure: How to Say No");
        course3.setDescription("This course provides practical strategies and builds confidence for resisting peer pressure related to drug use.");
        course3.setImage("https://example.com/images/peer_pressure.png");
        course3.setVideoUrl("https://www.youtube.com/watch?v=T4311g2-g-Q");
        course3.setStatus("Active");
        course3.setDuration(30);
        course3.setCreatedAt(Instant.now());
        course3.setAgeGroup(ageGroupAdolescents);
        course3.setQuestions(new ArrayList<>());

        // Q3.1
        OnlineCourseQuestion q1c3 = createQuestion("Which of the following is the most effective and assertive refusal strategy?", course3);
        q1c3.getAnswers().addAll(Arrays.asList(
                createAnswer("Saying 'maybe later' to avoid conflict.", false, q1c3),
                createAnswer("Firmly saying 'No, thanks' while making eye contact.", true, q1c3),
                createAnswer("Ignoring the person and walking away without a word.", false, q1c3),
                createAnswer("Laughing it off and pretending it's a joke.", false, q1c3)
        ));
        course3.getQuestions().add(q1c3);

        // Q3.2
        OnlineCourseQuestion q2c3 = createQuestion("If you're at a party and feel pressured, what is a smart move?", course3);
        q2c3.getAnswers().addAll(Arrays.asList(
                createAnswer("Agree to do it just to fit in.", false, q2c3),
                createAnswer("Use an excuse, like 'I have an early morning,' and leave.", true, q2c3),
                createAnswer("Start an argument to show you're tough.", false, q2c3),
                createAnswer("Quietly stay but feel uncomfortable.", false, q2c3)
        ));
        course3.getQuestions().add(q2c3);

        // Q3.3
        OnlineCourseQuestion q3c3 = createQuestion("Who is the BEST person to talk to if you're consistently feeling pressured by friends?", course3);
        q3c3.getAnswers().addAll(Arrays.asList(
                createAnswer("A younger sibling.", false, q3c3),
                createAnswer("The most popular person at school.", false, q3c3),
                createAnswer("A trusted adult, like a parent, teacher, or school counselor.", true, q3c3),
                createAnswer("Someone online you don't know.", false, q3c3)
        ));
        course3.getQuestions().add(q3c3);

        // Q3.4
        OnlineCourseQuestion q4c3 = createQuestion("What does 'assertive' communication mean when saying no?", course3);
        q4c3.getAnswers().addAll(Arrays.asList(
                createAnswer("Being aggressive and yelling.", false, q4c3),
                createAnswer("Being passive and quiet.", false, q4c3),
                createAnswer("Being confident, clear, and respectful of yourself and others.", true, q4c3),
                createAnswer("Being sarcastic and making fun of the other person.", false, q4c3)
        ));
        course3.getQuestions().add(q4c3);

        // Q3.5
        OnlineCourseQuestion q5c3 = createQuestion("Having friends who don't use drugs can...", course3);
        q5c3.getAnswers().addAll(Arrays.asList(
                createAnswer("Make you unpopular.", false, q5c3),
                createAnswer("Make it easier to resist pressure and make healthy choices.", true, q5c3),
                createAnswer("Be boring.", false, q5c3),
                createAnswer("Not make any difference.", false, q5c3)
        ));
        course3.getQuestions().add(q5c3);

        courses.add(course3);

        // 3. Save all the Courses to the Database
        onlineCourseRepository.saveAll(courses);

        System.out.println("✅ Sample data has been initialized successfully. " + courses.size() + " courses created.");
    }

    // Helper method to reduce boilerplate code
    private OnlineCourseQuestion createQuestion(String text, OnlineCourse course) {
        OnlineCourseQuestion question = new OnlineCourseQuestion();
        question.setQuestionText(text);
        question.setOnlineCourse(course);
        question.setAnswers(new ArrayList<>());
        return question;
    }

    // Helper method to reduce boilerplate code
    private OnlineCourseAnswer createAnswer(String text, boolean isCorrect, OnlineCourseQuestion question) {
        OnlineCourseAnswer answer = new OnlineCourseAnswer();
        answer.setAnswerText(text);
        answer.setCorrect(isCorrect);
        answer.setQuestion(question);
        return answer;
    }
}