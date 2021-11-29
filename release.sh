#!/bin/sh



# 使用说明:
#eg:  ./release.sh -v 1.0.0 -m "the first release for version 1.0.0"
# -v 发布版本
# -m 发布的tag描述
# 默认会使用当前分支最后一个commit作为tag的提交
# 额外的, 可以通过 -i 指定commit的全hash 作为指定commit 提交
# eg:  ./release.sh  -i  632e50e36f694f67650350f2f3dc0985cb9142a0 -v 1.0.0 -m "the first release for version 1.0.0"

release_usage()
{
	echo "Usage: $0 [ -v release version ] [ -i commit_id, support null ] [ -m release messgae, support null ]" 1>&2
	exit 1
}
branch_support(){
  the_err_branch=`git rev-parse --abbrev-ref HEAD`
  echo "Branch Limit: only support main/dev/test as the release branch, ${the_err_branch} not allowed!!!"
}

COMMIT_BY_SPEC_SHA=
COMMIT_BY_SHORT_SHA=
COMMIT_BY_BRANCH=
COMMIT_BY_VERSION=
COMMIT_TAG_MESSAGE=
COMMIT_TAG_NAME=

# 参数解析
while getopts ":-i:-v:-m:" o; do
	case "${o}" in
		i)
			COMMIT_BY_SPEC_SHA=${OPTARG}
			;;
		v)
			COMMIT_BY_VERSION=${OPTARG}
			;;
		m)
			COMMIT_TAG_MESSAGE=${OPTARG}
			;;
		*)
			echo "ERROR"
			release_usage
			;;
	esac
done
shift $((OPTIND-1))

# 参数为空判断
if [ -z "${COMMIT_BY_VERSION}" ]; then
	echo "ERR: please input 'version' param.."
	release_usage
fi


git status &> /dev/null
is_git_repo=$?

if [ "${is_git_repo}" = "0" ]; then
	git config core.abbrev 8

	current_branch=`git rev-parse --abbrev-ref HEAD`
	case "${current_branch}" in
		"dev"):
			COMMIT_BY_BRANCH=dev
			;;
		"test"):
			COMMIT_BY_BRANCH=test
			;;
		"main"):
			COMMIT_BY_BRANCH=pd
			;;
		*)
			release_usage
			;;
	esac

	if [ -z "${COMMIT_BY_SPEC_SHA}" ]; then
		COMMIT_BY_SHORT_SHA=`git rev-parse --short HEAD`
	else
		git show --shortstat --pretty=oneline ${COMMIT_BY_SPEC_SHA} &> /dev/null
		is_git_commit_id=$?
		if [ "${is_git_commit_id}" = "0" ]; then
			COMMIT_BY_SHORT_SHA=${COMMIT_BY_SPEC_SHA:0:8}
		else
			COMMIT_BY_SHORT_SHA=`git rev-parse --short HEAD`
		fi
	fi

	COMMIT_TAG_NAME="${COMMIT_BY_BRANCH}-${COMMIT_BY_VERSION}-${COMMIT_BY_SHORT_SHA}"

	if [ -z "${COMMIT_TAG_MESSAGE}" ]; then
		COMMIT_TAG_MESSAGE="[PROJECT RELEASE]: ENV='${COMMIT_BY_BRANCH}' VERSION='${COMMIT_BY_VERSION}' COMMIT_SHORT_ID='${COMMIT_BY_SHORT_SHA}' TAG='${COMMIT_TAG_NAME}'"
	fi

	if [ -z "${COMMIT_BY_SPEC_SHA}" ]; then
		git tag -f -m "${COMMIT_TAG_MESSAGE}" -a "${COMMIT_TAG_NAME}"
		echo "PROJECT Git Repo Tag success: ${COMMIT_TAG_NAME}, ${COMMIT_TAG_MESSAGE}"
		git push origin refs/tags/${COMMIT_TAG_NAME} --verbose &> /dev/null
		is_push_tag_success=$?
		if [ "${is_push_tag_success}" = "0" ]; then
			echo "PROJECT Git Repo Tag push success: [new tag] refs/tags/${COMMIT_TAG_NAME}"
		fi
	else
		git tag -f -m "${COMMIT_TAG_MESSAGE}" -a "${COMMIT_TAG_NAME}" ${COMMIT_BY_SHORT_SHA}
		echo "PROJECT Git Repo Tag success: ${COMMIT_TAG_NAME}, ${COMMIT_TAG_MESSAGE}"
		git push origin refs/tags/${COMMIT_TAG_NAME} --verbose &> /dev/null
		is_push_tag_success=$?
		if [ "${is_push_tag_success}" = "0" ]; then
			echo "PROJECT Git Repo Tag push success: [new tag] refs/tags/${COMMIT_TAG_NAME}"
		fi

	fi
else
	echo "ERR: is not a real git repo.."
	exit 1
fi

exit 0
