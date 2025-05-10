function updateMemberList() {
    const memberType = document.getElementById("memberTypeSelect").value;

    // AJAX 요청을 보내서 선택된 memberType에 해당하는 멤버 목록을 가져옵니다.
    fetch(`/v1/members/code/${memberType}`)
        .then(response => response.json())
        .then(data => {
            const memberListContainer = document.getElementById("memberListContainer");
            memberListContainer.innerHTML = ''; // 기존 멤버 목록 비우기

            // 서버에서 받은 멤버 데이터로 새롭게 목록을 그립니다.
            data.data.forEach(member => {
                const progressClass = member.ratio >= 75 ? 'bg-success' : member.ratio >= 50 ? 'bg-warning' : 'bg-danger';

                const memberCard = `
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">
                                    <a href="/members/${member.no}" class="text-decoration-none text-dark">
                                        <span>${member.name}</span>
                                    </a>
                                </h5>
                                <p class="card-text mb-1">
                                    <strong>전체 프로젝트 수:</strong> <span>${member.total}</span><br>
                                    <strong>완료 프로젝트 수:</strong> <span>${member.completeProjectsNum}</span>
                                </p>
                                <div class="progress">
                                    <div class="progress-bar ${progressClass}" role="progressbar" style="width: ${member.ratio}%"
                                         aria-valuemin="0" aria-valuemax="100">
                                        ${member.ratio}%
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
                memberListContainer.innerHTML += memberCard;
            });
        })
        .catch(error => console.error("Error fetching members:", error));
}

document.addEventListener("DOMContentLoaded", () => {
    updateMemberList();
});