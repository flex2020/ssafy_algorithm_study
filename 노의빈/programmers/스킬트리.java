class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (int i=0; i<skill_trees.length; i++) {
            String currentSkill = skill_trees[i]; // 현재 검증할 스킬트리
            for (int j=currentSkill.length()-1; j>=0; j--) {
                if (skill.contains(currentSkill.charAt(j) + "")) continue;
                currentSkill = currentSkill.replaceAll(currentSkill.charAt(j) + "", "");
            }
            System.out.println(currentSkill);
            if (skill.indexOf(currentSkill) == 0) answer += 1;
        }
        return answer;
    }
}
