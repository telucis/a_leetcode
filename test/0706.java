/**
 * dfs-base
 */
//216
public  List<List<Integer>> combinationSum3(int k, int n) {
	List<List<Integer>> ans = new ArrayList<>();
	backtrack(ans, new LinkedList<>(), 1, k, n);
	return ans;
}
private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int start, int k, int n) {
	if (n==0 && k==0) ans.add(new ArrayList<>(tmp));
	else if (n==0 || k==0) return;
	else {
		for (int i=start; i<10; i++) {
			tmp.add(i);
			backtrack(ans, tmp, i+1, k-1, n-i);
			tmp.removeLast();
		}
	}
}

//77
public List<List<Integer>> combine(int n, int k) {
	List<List<Integer>> ans = new ArrayList<>();

}
private void backtrack(List<List<Integer>> ans, List<Integer> tmp, int start, int n, int k) {
	if (k==0) ans.add(new ArrayList<>(tmp));
	else {
		for (int i=start; i<n; i++) {
			tmp.add(i);
			backtrack(ans, tmp, i+1, n, k-1);
			tmp.removeLast();
		}
	}
}

//131
public List<List<String>> partition(String s) {
	List<List<String>> ans = new ArrayList<>();
	backtrack(ans, new LinkedList<>(), 0, s);
	return ans;
}
private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int start, String s) {
	if (start==s.length()) ans.add(new ArrayList<>(tmp));
	else {
		for (int i=start; i<s.length(); i++) {
			if (isPalindrome(s, start, i)) {
				tmp.add(s.subString(start, i+1));
				backtrack(ans, tmp, i+1, s);
				tmp.removeLast();
			}
		}
	}
}
private boolean isPalindrome(String s, int low, int hight) {
	while(low<hight) {
		if (s.charAt(low++) != s.charAt(hight--)) return false;
	}
	return true;
}

//46
public List<List<Integer>> permute(int[] nums) {
	List<List<Integer>> ans = new ArrayList<>();
	backtrack(ans, new LinkedList<>(), nums);
	return ans;
}
private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] nums) {
	if (tmp.size() == nums.length) ans.add(new ArrayList<>(tmp));
	else {
		for (int i=0; i<nums.length; i++) {
			if (tmp.contains(nums[i])) continue;
			tmp.add(nums[i]);
			backtrack(ans, tmp, nums);
			tmp.removeLast();
		}
	}
}

//47
public List<List<Integer>> permuteUnique(int[] nums) {
	List<List<Integer>> ans = new ArrayList<>();
	Arrays.sort(nums);
	backtrack(ans, new LinkedList<>(), nums, new boolean[nums.length]);
	return ans;
}
private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] nums, boolean[] used) {
	if (tmp.size() == nums.length) ans.add(new ArrayList<>(tmp));
	else {
		for (int i=0; i<nums.length; i++) {
			if (used[i] || i>0 && nums[i]==nums[i-1] && !used[i-1]) continue;
			tmp.add(nums[i]);
			used[i] = true;
			backtrack(ans, tmp, nums, used);
			tmp.removeLast();
			used[i] = false;
		}
	}
}

//78
public List<List<Integer>> subsets(int[] nums) {
	List<List<Integer>> ans = new ArrayList<>();
	backtrack(ans, new LinkedList<>(), nums, 0);
	return ans;
}
private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] nums, int start) {
	ans.add(new ArrayList<>(tmp));
	for (int i=start; i<nums.length; i++) {
		tmp.add(nums[i]);
		backtrack(ans, tmp, nums, i+1);
		tmp.removeLast();
	}
}

//90
public List<List<Integer>> subsetsWithDup(int[] nums) {
	List<List<Integer>> ans = new ArrayList<>();
	Arrays.sort(nums);
	backtrack(ans, new LinkedList<>(), nums, 0);
	return ans;
}
private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] nums, int start) {
	ans.add(new ArrayList<>(tmp));
	for (int i=start; i<nums.length; i++) {
		if (i>start && nums[i]==nums[i-1]) continue;
		tmp.add(i);
		backtrack(ans, tmp, nums, i+1);
		tmp.removeLast();
	}
}

//797
public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
	List<List<Integer>> ans = new ArrayList<>();
	backtrack(ans, new LinkedList<>(), graph, 0);
	return ans;
}
private void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[][] graph, int start) {
	if (start==graph.length) ans.add(new ArrayList<>(tmp));
	else {
		for (int i=0; i<graph[start].length; i++) {
			tmp.add(graph[start][i]);
			backtrack(ans, tmp, graph, start+1);
			tmp.removeLast();
		}
	}
}

//526
int count;
public int countArrangement(int N) {
	if (N==0) return 0;
	helper(N, 1, new boolean[N+1]);
	return count;
}
private void helper(int N, int pos, boolean[] used) {
	if (pos>N) count++;
	else {
		for (int i=1; i<=N; i++) {
			if (used[i] || i%pos!=0 || pos%i!=0) continue;
			used[i] = true;
			helper(N, pos+1, used);
			used[i] = false;
		}
	}
}

//491
public List<List<Integer>> findSubsequences(int[] nums) {
	List<List<Integer>> ans = new ArrayList<>();
	backtrack(ans, new LinkedList<>(), nums, 0);
	return ans;
}
public void backtrack(List<List<Integer>> ans, LinkedList<Integer> tmp, int[] nums, int pos) {
	if (tmp.size()>1) ans.add(new ArrayList<>(tmp));
	Set<Integer> used = new HashSet<>();
	for (int i=pos; i<nums.length; i++) {
		if (used.contains(nums[i])) continue;
		if (tmp.size()==0 || tmp.peekLast()<=nums[i]) {
			tmp.add(nums[i]);
			used.add(nums[i]);
			backtrack(ans, tmp, nums, i+1);
			tmp.removeLast();
		}
	}
}



