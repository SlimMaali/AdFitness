<?php

namespace AppBundle\Controller;

use AppBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class UserController extends Controller
{
    public function registerAction(Request $request)
    {
        $user = new User();
        $em = $this->getDoctrine()->getManager();
        $user->setUsername($request->get('username'));
        $user->setPassword($request->get('password'));
        $user->setEmail($request->get('email'));
        $user->setBirthday(new \DateTime($request->get('birthday')));
        $user->setRole($request->get('role'));
        $user->setFirstName($request->get('firstName'));
        $user->setLastName($request->get('lastName'));
        $user->setPhone($request->get('phone'));
        $em->persist($user);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formated = $serializer->normalize($user);
        return new JsonResponse($formated);
    }
    public function loginAction(Request $request)
    {
        $user=$this->getDoctrine()->getManager()
            ->getRepository('AppBundle:User')->findBy(array('username'=>$request->get('username'),'password'=>$request->get('password')));
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formated=$serializer->normalize($user);
        return new JsonResponse($formated);
    }

}
